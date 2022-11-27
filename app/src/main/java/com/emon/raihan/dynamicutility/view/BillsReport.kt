package com.emon.raihan.dynamicutility.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View.OnFocusChangeListener
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.adaptar.DropdownListAdaptar
import com.emon.raihan.dynamicutility.model.CodeDesOptions
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import com.emon.raihan.dynamicutility.util.CustomDailog
import com.emon.raihan.dynamicutility.util.CustomDailog.Companion.addJpgSignatureToGallery
import com.emon.raihan.dynamicutility.util.CustomDailog.Companion.requestPermission
import com.emon.raihan.dynamicutility.util.CustomDailog.Companion.verifyStoragePermissions
import com.emon.raihan.dynamicutility.util.DateUtil
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class BillsReport : CustomAppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView

    private lateinit var sp_bill_type_value: MaterialAutoCompleteTextView
    private lateinit var et_from_date: TextInputEditText
    private lateinit var et_to_date: TextInputEditText
    private lateinit var btnSearch: Button
    private lateinit var btn_today: Button
    private lateinit var btnLastWeek: Button
    private lateinit var btnLastMonth: Button
    private lateinit var peram_layout: LinearLayout

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var billType = ""
    var fromDate = ""
    var toDate = ""
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bills_report)

        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)

        sp_bill_type_value = findViewById(R.id.sp_bill_type_value)
        et_from_date = findViewById(R.id.et_from_date)
        et_to_date = findViewById(R.id.et_to_date)
        btnSearch = findViewById(R.id.btnSearch)
        btn_today = findViewById(R.id.btn_today)
        btnLastWeek = findViewById(R.id.btnLastWeek)
        btnLastMonth = findViewById(R.id.btnLastMonth)
        peram_layout = findViewById(R.id.peram_layout)

        setSupportActionBar(toolbar)
        toolbar_title.text = "Bills Report"
        // Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        //  supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //  supportActionBar!!.title = "About Me"

        iv_header_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        verifyStoragePermissions(this)
        requestPermission(this)

        codeDesOptions.add(CodeDesOptions("Dhaka Wasa", "DWASA"))
        codeDesOptions.add(CodeDesOptions("Chattagram Wasa", "CWASA"))
        codeDesOptions.add(CodeDesOptions("Khulna Wasa", "KWASA"))
        codeDesOptions.add(CodeDesOptions("Rajshahi Wasa", "RWASA"))
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, codeDesOptions)
        sp_bill_type_value.setAdapter(arrayAdapter)


        fromDate = DateUtil.geCurrendDate()
        toDate = DateUtil.geCurrendDate()
        et_from_date.setText(fromDate)
        et_to_date.setText(toDate)

        //  et_from_date.setOnClickListener { fromDate() }
        //  et_to_date.setOnClickListener { toDate() }

        et_from_date.setOnClickListener { CustomDailog.fromDate(this, et_from_date) }
        et_to_date.setOnClickListener { CustomDailog.toDate(this, et_to_date) }

        btnLastMonth.setOnClickListener {
            fromDate = DateUtil.getCommission_date(30)
            toDate = DateUtil.geCurrendDate()

            et_from_date.setText(fromDate)
            et_to_date.setText(toDate)

        }

        btnLastWeek.setOnClickListener {
            fromDate = DateUtil.getCommission_date(7)
            toDate = DateUtil.geCurrendDate()

            et_from_date.setText(fromDate)
            et_to_date.setText(toDate)

        }

        btn_today.setOnClickListener {
            fromDate = DateUtil.geCurrendDate()
            toDate = DateUtil.geCurrendDate()

            et_from_date.setText(fromDate)
            et_to_date.setText(toDate)

        }

        sp_bill_type_value.setOnItemClickListener { parent, arg1, position, id ->
            billType = codeDesOptions[position].code.toString()
        }

        sp_bill_type_value.setOnClickListener {
            showDropDoownDialog()
        }

        btnSearch.setOnClickListener {
            val bitmap = CustomDailog.getBitmapFromView(peram_layout)
            if (addJpgSignatureToGallery(this, bitmap)) {
                Toast.makeText(
                    this@BillsReport,
                    "Voucher saved into the Gallery",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this@BillsReport,
                    "Unable to store the Voucher",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>, grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_EXTERNAL_STORAGE -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.isEmpty()
                    || grantResults[0] != PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(
                        this@BillsReport,
                        "Cannot write images to external storage",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun showDropDoownDialog() {
        lateinit var et_bill_type_value: TextInputEditText
        lateinit var mAdapter: DropdownListAdaptar
        lateinit var ivClose: ImageView
        lateinit var dropdown_recycler: RecyclerView
        val dialog = AlertDialog.Builder(this).setCancelable(false)
        val inflater = LayoutInflater.from(this)
        val regLayout = inflater.inflate(R.layout.dialog_dropdown_picker, null)
        et_bill_type_value = regLayout.findViewById(R.id.et_bill_type_value)
        dropdown_recycler = regLayout.findViewById(R.id.dropdown_recycler)
        ivClose = regLayout.findViewById(R.id.ivClose)
        dialog.setView(regLayout)
        val alertDialog = dialog.create()
        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val mLayoutManager = LinearLayoutManager(this)
        dropdown_recycler.layoutManager = mLayoutManager
        dropdown_recycler.itemAnimator = DefaultItemAnimator()

        mAdapter =
            DropdownListAdaptar(
                codeDesOptions, dialog,
                object : DropdownListAdaptar.OnItemClickListener {
                    override fun onItemClick(item: CodeDesOptions) {
                        billType = item.code.toString()
                        // bill_type_title.text = item.desc
                        sp_bill_type_value.setText(item.desc)

                        alertDialog.dismiss()
                    }
                })

        dropdown_recycler.adapter = mAdapter
        mAdapter.notifyDataSetChanged()

        et_bill_type_value.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                mAdapter.filter.filter(s)
            }
        })

        ivClose.setOnClickListener { alertDialog.dismiss() }


        alertDialog.show()

    }

}