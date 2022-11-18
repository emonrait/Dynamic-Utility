package com.emon.raihan.dynamicutility.view

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.model.CodeDesOptions
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.DateUtil
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class BillsReport : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var sp_bill_type_value: MaterialAutoCompleteTextView
    private lateinit var et_from_date: TextInputEditText
    private lateinit var et_to_date: TextInputEditText
    private lateinit var btnSearch: Button
    private lateinit var btn_today: Button
    private lateinit var btnLastWeek: Button
    private lateinit var btnLastMonth: Button

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var billType = ""
    var fromDate = ""
    var toDate = ""
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

        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Bill Payment Report"

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }


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

        et_from_date.setOnClickListener { fromDate() }
        et_to_date.setOnClickListener { toDate() }

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

    }

    fun fromDate() {
        // Get Current Date
        val mYear: Int
        val mMonth: Int
        val mDay: Int
        var mHour: Int
        var mMinute: Int
        // Get Current Date
        val c = Calendar.getInstance()
        mYear = c[Calendar.YEAR]
        mMonth = c[Calendar.MONTH]
        mDay = c[Calendar.DAY_OF_MONTH]


        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth ->
                var month1: String? = null
                var day1: String? = null
                month1 = if (monthOfYear < 9) {
                    "0" + (monthOfYear + 1)
                } else {
                    "" + (monthOfYear + 1)
                }
                day1 = if (view!!.dayOfMonth <= 9) {
                    "0" + view.dayOfMonth
                } else {
                    "" + view.dayOfMonth
                }

                et_from_date.setText("$day1/$month1/$year")
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }


    fun toDate() {
        // Get Current Date
        val mYear: Int
        val mMonth: Int
        val mDay: Int
        var mHour: Int
        var mMinute: Int
        // Get Current Date
        val c = Calendar.getInstance()
        mYear = c[Calendar.YEAR]
        mMonth = c[Calendar.MONTH]
        mDay = c[Calendar.DAY_OF_MONTH]


        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth ->
                var month1: String? = null
                var day1: String? = null
                month1 = if (monthOfYear < 9) {
                    "0" + (monthOfYear + 1)
                } else {
                    "" + (monthOfYear + 1)
                }
                day1 = if (view!!.dayOfMonth <= 9) {
                    "0" + view.dayOfMonth
                } else {
                    "" + view.dayOfMonth
                }

                et_to_date.setText("$day1/$month1/$year")
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }
}