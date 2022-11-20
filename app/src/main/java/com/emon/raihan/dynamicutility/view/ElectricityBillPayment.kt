package com.emon.raihan.dynamicutility.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.model.CodeDesOptions
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import com.emon.raihan.dynamicutility.util.CustomDailog
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class ElectricityBillPayment : CustomAppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var sp_bill_type_value: MaterialAutoCompleteTextView
    private lateinit var year_month_layout: LinearLayout
    private lateinit var meter_no_input_cardview: CardView
    private lateinit var input_amount_cardview: CardView
    private lateinit var btn_validate_cardview: CardView
    private lateinit var customer_code_input_cardview: CardView
    private lateinit var sp_year_value: MaterialAutoCompleteTextView
    private lateinit var sp_month_value: MaterialAutoCompleteTextView
    private lateinit var sp_month_input: TextInputLayout
    private lateinit var sp_year_input: TextInputLayout
    private lateinit var btn_validate: AppCompatButton
    private lateinit var et_customer_code_value: TextInputEditText
    private lateinit var et_meter_no_value: TextInputEditText
    private lateinit var et_amount_value: TextInputEditText

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var billType = ""
    var year = ""
    var month = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electricity_bill_payment)
        toolbar = findViewById(R.id.toolbar)
        sp_bill_type_value = findViewById(R.id.sp_bill_type_value)
        year_month_layout = findViewById(R.id.year_month_layout)
        meter_no_input_cardview = findViewById(R.id.meter_no_input_cardview)
        input_amount_cardview = findViewById(R.id.input_amount_cardview)
        btn_validate_cardview = findViewById(R.id.btn_validate_cardview)
        customer_code_input_cardview = findViewById(R.id.customer_code_input_cardview)
        sp_year_value = findViewById(R.id.sp_year_value)
        sp_month_value = findViewById(R.id.sp_month_value)
        sp_month_input = findViewById(R.id.sp_month_input)
        sp_year_input = findViewById(R.id.sp_year_input)
        btn_validate = findViewById(R.id.btn_validate)
        et_customer_code_value = findViewById(R.id.et_customer_code_value)
        et_meter_no_value = findViewById(R.id.et_meter_no_value)
        et_amount_value = findViewById(R.id.et_amount_value)

        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Electricity Bill Payment"

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        btn_validate_cardview.visibility = View.GONE
        year_month_layout.visibility = View.GONE
        meter_no_input_cardview.visibility = View.GONE
        input_amount_cardview.visibility = View.GONE
        customer_code_input_cardview.visibility = View.GONE

        codeDesOptions.add(CodeDesOptions("DESCO Postpaid", "DESCO"))
        codeDesOptions.add(CodeDesOptions("DESCO Prepaid", "DESCOPRE"))
        codeDesOptions.add(CodeDesOptions("DPDC Postpaid", "DPDC"))
        codeDesOptions.add(CodeDesOptions("DPDC Prepaid", "DPDCPRE"))
        codeDesOptions.add(CodeDesOptions("NESCO Postpaid", "NESCO"))
        codeDesOptions.add(CodeDesOptions("NESCO Prepaid", "NESCOPRE"))
        codeDesOptions.add(CodeDesOptions("Palli Biddyt Postpaid", "PALLI"))
        codeDesOptions.add(CodeDesOptions("Palli Biddyt Prepaid", "PALLIPRE"))
        codeDesOptions.add(CodeDesOptions("BPDB Postpaid", "BPDB"))
        codeDesOptions.add(CodeDesOptions("BPDB Prepaid", "BPDBPRE"))
        codeDesOptions.add(CodeDesOptions("BPDB Prepaid Sylhet", "BPDBSPRE"))
        codeDesOptions.add(CodeDesOptions("Westzone Postpaid", "WESTZ"))
        codeDesOptions.add(CodeDesOptions("Westzone Prepaid", "WESTZPRE"))

        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, codeDesOptions)
        sp_bill_type_value.setAdapter(arrayAdapter)


        sp_bill_type_value.setOnItemClickListener { parent, arg1, position, id ->
            billType = codeDesOptions[position].code.toString()
            if (billType.endsWith("PRE")) {
                year_month_layout.visibility = View.GONE
                meter_no_input_cardview.visibility = View.VISIBLE
                input_amount_cardview.visibility = View.VISIBLE
                btn_validate_cardview.visibility = View.VISIBLE
                customer_code_input_cardview.visibility = View.VISIBLE

            } else {
                year_month_layout.visibility = View.VISIBLE
                meter_no_input_cardview.visibility = View.GONE
                input_amount_cardview.visibility = View.GONE
                btn_validate_cardview.visibility = View.VISIBLE
                customer_code_input_cardview.visibility = View.VISIBLE

            }

        }


        sp_year_value.setOnClickListener {
            year=   CustomDailog.createYearPicker(this, sp_year_value)

        }

        sp_year_input.setOnClickListener {
          year=  CustomDailog.createYearPicker(this, sp_year_value)
        }

        sp_month_value.setOnClickListener {
            month=  CustomDailog.createMonthPicker(this, sp_month_value)
        }
        sp_month_input.setOnClickListener {
            month = CustomDailog.createMonthPicker(this, sp_month_value)
        }

        btn_validate.setOnClickListener {
            if (billType.isEmpty()) {
                Toast.makeText(this, "Please Select Bill Type", Toast.LENGTH_SHORT).show()
            } else if (billType.endsWith("PRE")) {
                if (et_customer_code_value.text.toString().isEmpty()) {
                    Toast.makeText(this, "Please Enter Customer Code", Toast.LENGTH_SHORT).show()
                } else if (et_meter_no_value.text.toString().isEmpty()) {
                    Toast.makeText(this, "Please Enter Meter No", Toast.LENGTH_SHORT).show()
                } else if (et_amount_value.text.toString().isEmpty()) {
                    Toast.makeText(this, "Please Enter Recharge Amount", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (year.isEmpty()) {
                    Toast.makeText(this, "Please Select Bill Year", Toast.LENGTH_SHORT).show()
                } else if (month.isEmpty()) {
                    Toast.makeText(this, "Please Select Bill Month", Toast.LENGTH_SHORT).show()
                } else if (et_customer_code_value.text.toString().isEmpty()) {
                    Toast.makeText(this, "Please Enter Customer Code", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}