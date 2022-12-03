package com.emon.raihan.dynamicutility.view.welcome.mfs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.adaptar.DropdownListAdaptar
import com.emon.raihan.dynamicutility.model.CodeDesOptions
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import com.emon.raihan.dynamicutility.view.MainActivity
import com.emon.raihan.dynamicutility.view.welcome.Welcome
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText

class MfsTransfer : CustomAppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView
    private lateinit var sp_bill_type_value: MaterialAutoCompleteTextView


    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var billType = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mfs_transfer)

        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)
        sp_bill_type_value = findViewById(R.id.sp_bill_type_value)

        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.mfs_transfer)

        iv_header_back.setOnClickListener {
            val intent = Intent(this, Welcome::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        codeDesOptions.add(CodeDesOptions("bKash", "BK"))
        codeDesOptions.add(CodeDesOptions("Nagad", "NG"))
        codeDesOptions.add(CodeDesOptions("Rocket", "RK"))
        codeDesOptions.add(CodeDesOptions("Tap", "TAP"))

        sp_bill_type_value.setOnClickListener {
            showDropDoownDialog()
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