package com.emon.raihan.dynamicutility.view

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.media.MediaScannerConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.emon.raihan.dynamicutility.R
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
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class BillsReport : CustomAppCompatActivity() {
    private lateinit var toolbar: Toolbar
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
        sp_bill_type_value = findViewById(R.id.sp_bill_type_value)
        et_from_date = findViewById(R.id.et_from_date)
        et_to_date = findViewById(R.id.et_to_date)
        btnSearch = findViewById(R.id.btnSearch)
        btn_today = findViewById(R.id.btn_today)
        btnLastWeek = findViewById(R.id.btnLastWeek)
        btnLastMonth = findViewById(R.id.btnLastMonth)
        peram_layout = findViewById(R.id.peram_layout)

        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Bill Payment Report"

        toolbar.setNavigationOnClickListener {
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


}