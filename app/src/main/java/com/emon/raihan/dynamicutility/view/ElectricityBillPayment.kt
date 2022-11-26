package com.emon.raihan.dynamicutility.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.model.CodeDesOptions
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import com.emon.raihan.dynamicutility.util.CustomDailog
import com.emon.raihan.dynamicutility.util.DialogCustom
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ElectricityBillPayment : CustomAppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView
    private lateinit var iv_bill_type_logo: ImageView
    private lateinit var bill_type_title: TextView
    private lateinit var bill_type_view_layout: LinearLayout

    private lateinit var sp_bill_type_value: MaterialAutoCompleteTextView
    private lateinit var year_month_layout: LinearLayout
    private lateinit var meter_no_input_cardview: CardView
    private lateinit var input_amount_cardview: CardView
    private lateinit var btn_validate_cardview: CardView
    private lateinit var customer_code_input_cardview: CardView
    private lateinit var palli_type_input_cardview: CardView
    private lateinit var sp_year_value: MaterialAutoCompleteTextView
    private lateinit var sp_month_value: MaterialAutoCompleteTextView
    private lateinit var sp_palli_type_input_value: MaterialAutoCompleteTextView
    private lateinit var sp_month_input: TextInputLayout
    private lateinit var sp_year_input: TextInputLayout
    private lateinit var customer_code_input: TextInputLayout
    private lateinit var btn_validate: AppCompatButton
    private lateinit var et_customer_code_value: TextInputEditText
    private lateinit var et_meter_no_value: TextInputEditText
    private lateinit var et_amount_value: TextInputEditText

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var codeDesOptionsPalli: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var billType = ""
    var billTypePalli = ""
    var year = ""
    var month = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electricity_bill_payment)

        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)
        iv_bill_type_logo = findViewById(R.id.iv_bill_type_logo)
        bill_type_title = findViewById(R.id.bill_type_title)
        bill_type_view_layout = findViewById(R.id.bill_type_view_layout)
        customer_code_input = findViewById(R.id.customer_code_input)
        palli_type_input_cardview = findViewById(R.id.palli_type_input_cardview)


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
        sp_palli_type_input_value = findViewById(R.id.sp_palli_type_input_value)
        //  palli_type_input = findViewById(R.id.palli_type_input)

        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.electricity_biill_payment)

        iv_header_back.setOnClickListener {
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
        // Palli Bill Type
        codeDesOptionsPalli.add(CodeDesOptions("Official Receipt", "10"))
        codeDesOptionsPalli.add(CodeDesOptions("Application Fee", "19"))
        codeDesOptionsPalli.add(CodeDesOptions("Demand Note/Consumer Deposit", "20"))
        codeDesOptionsPalli.add(CodeDesOptions("Advance Electricity Bill", "30"))
        codeDesOptionsPalli.add(CodeDesOptions("Electricity Bill", "50"))

        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, codeDesOptions)
        sp_bill_type_value.setAdapter(arrayAdapter)

        val arrayAdapterPalli = ArrayAdapter(this, R.layout.dropdown_item, codeDesOptionsPalli)
        sp_palli_type_input_value.setAdapter(arrayAdapterPalli)

        sp_bill_type_value.setOnClickListener {
            CustomDailog.showDropDoownDialog(this, codeDesOptions, sp_bill_type_value)
        }


        sp_bill_type_value.setOnItemClickListener { parent, arg1, position, id ->
            billType = codeDesOptions[position].code.toString()
            bill_type_title.text = codeDesOptions[position].desc.toString()
            bill_type_view_layout.visibility = View.VISIBLE
            iv_bill_type_logo.setImageResource(R.drawable.electricity_bill)
            if (billType.endsWith("PRE")) {
                year_month_layout.visibility = View.GONE
                meter_no_input_cardview.visibility = View.GONE
                palli_type_input_cardview.visibility = View.GONE
                input_amount_cardview.visibility = View.VISIBLE
                btn_validate_cardview.visibility = View.VISIBLE
                customer_code_input_cardview.visibility = View.VISIBLE

            } else if (billType.equals("PALLI")) {
                customer_code_input.hint = "Enter SMS Bill Account Number"
                year_month_layout.visibility = View.VISIBLE
                palli_type_input_cardview.visibility = View.VISIBLE
                meter_no_input_cardview.visibility = View.GONE
                input_amount_cardview.visibility = View.GONE
                btn_validate_cardview.visibility = View.VISIBLE
                customer_code_input_cardview.visibility = View.VISIBLE

            } else {
                year_month_layout.visibility = View.VISIBLE
                meter_no_input_cardview.visibility = View.GONE
                palli_type_input_cardview.visibility = View.GONE
                input_amount_cardview.visibility = View.GONE
                btn_validate_cardview.visibility = View.VISIBLE
                customer_code_input_cardview.visibility = View.VISIBLE

            }

        }

        sp_palli_type_input_value.setOnItemClickListener { parent, arg1, position, id ->
            billTypePalli = codeDesOptionsPalli[position].code.toString()
        }

        sp_year_value.setOnClickListener {
            CustomDailog.createYearPicker(this, sp_year_value)
        }

        sp_year_input.setOnClickListener {
            CustomDailog.createYearPicker(this, sp_year_value)

        }

        sp_month_value.setOnClickListener {
            CustomDailog.createMonthPicker(this, sp_month_value)
        }
        sp_month_input.setOnClickListener {
            CustomDailog.createMonthPicker(this, sp_month_value)

        }

        btn_validate.setOnClickListener {
            Toast.makeText(this, billType, Toast.LENGTH_SHORT).show()
            Log.d("billType-->", billType)
            if (billType.isEmpty()) {
                Toast.makeText(this, "Please Select Bill Type", Toast.LENGTH_SHORT).show()
                DialogCustom.showErrorMessage(this, "Please Select Bill Type")
            } else {
                if (billType.endsWith("PRE")) {
                    if (et_customer_code_value.text.toString().isEmpty()) {
                        et_customer_code_value.requestFocus()
                        Toast.makeText(this, "Please Enter Customer Code", Toast.LENGTH_SHORT)
                            .show()
                        DialogCustom.showErrorMessage(this, "Please Enter Customer Code")

                    } else if (et_amount_value.text.toString().isEmpty()) {
                        et_amount_value.requestFocus()

                        Toast.makeText(this, "Please Enter Recharge Amount", Toast.LENGTH_SHORT)
                            .show()
                        DialogCustom.showErrorMessage(this, "Please Enter Recharge Amount")

                    } else {
                        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                        DialogCustom.showSuccessMessage(this, "Success!")

                    }
                } else if (billType == "PALLI") {
                    if (sp_year_value.text.toString().isEmpty()) {
                        Toast.makeText(this, "Please Select Bill Year", Toast.LENGTH_SHORT).show()
                        DialogCustom.showErrorMessage(this, "Please Select Bill Year")

                    } else if (sp_month_value.text.toString().isEmpty()) {
                        Toast.makeText(this, "Please Select Bill Month", Toast.LENGTH_SHORT).show()
                        DialogCustom.showErrorMessage(this, "Please Select Bill Month")
                    } else if (billTypePalli.isEmpty()) {
                        Toast.makeText(
                            this,
                            "Please Select Palli Bill Payment Type",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        DialogCustom.showErrorMessage(this, "Please Select Palli Bill Payment Type")

                    } else if (et_customer_code_value.text.toString().isEmpty()) {
                        et_customer_code_value.requestFocus()
                        Toast.makeText(
                            this,
                            "Please Enter SMS Bill Account Number",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        DialogCustom.showErrorMessage(this, "Please Enter SMS Bill Account Number")

                    } else {
                        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                        Log.d("value-->", "Success")
                        DialogCustom.showSuccessMessage(this, "Success!")

                    }
                } else {
                    if (sp_year_value.text.toString().isEmpty()) {
                        Toast.makeText(this, "Please Select Bill Year", Toast.LENGTH_SHORT).show()
                        DialogCustom.showErrorMessage(this, "Please Select Bill Year")
                    } else if (sp_month_value.text.toString().isEmpty()) {
                        Toast.makeText(this, "Please Select Bill Month", Toast.LENGTH_SHORT).show()
                        DialogCustom.showErrorMessage(this, "Please Select Bill Month")
                    } else if (et_customer_code_value.text.toString().isEmpty()) {
                        et_customer_code_value.requestFocus()
                        Toast.makeText(this, "Please Enter Customer Code", Toast.LENGTH_SHORT)
                            .show()
                        DialogCustom.showErrorMessage(this, "Please Enter Customer Code")

                    } else {
                        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                        DialogCustom.showSuccessMessage(this, "Success!")
                    }

                }
            }
        }
    }
}