package com.emon.raihan.dynamicutility.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.model.CodeDesOptions
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import com.emon.raihan.dynamicutility.util.CustomDailog
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class EntertainmentBillPayment : CustomAppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var sp_bill_type_value: MaterialAutoCompleteTextView
    private lateinit var sp_year_value: MaterialAutoCompleteTextView
    private lateinit var sp_month_value: MaterialAutoCompleteTextView
    private lateinit var customer_code_input: TextInputLayout
    private lateinit var sp_month_input: TextInputLayout
    private lateinit var sp_year_input: TextInputLayout

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var billType = ""
    var year = ""
    var month = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entertainment_bill_payment)

        toolbar = findViewById(R.id.toolbar)
        sp_bill_type_value = findViewById(R.id.sp_bill_type_value)
        customer_code_input = findViewById(R.id.customer_code_input)
        sp_year_value = findViewById(R.id.sp_year_value)
        sp_month_value = findViewById(R.id.sp_month_value)
        sp_month_input = findViewById(R.id.sp_month_input)
        sp_year_input = findViewById(R.id.sp_year_input)

        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Entertainment Bill Payment"

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }


        codeDesOptions.add(CodeDesOptions("Dhaka Wasa", "DWASA"))
        codeDesOptions.add(CodeDesOptions("Chattagram Wasa", "CWASA"))
        codeDesOptions.add(CodeDesOptions("Khulna Wasa (Metered)", "KWASA"))
        codeDesOptions.add(CodeDesOptions("Rajshahi Wasa", "RWASA"))
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, codeDesOptions)
        sp_bill_type_value.setAdapter(arrayAdapter)


        sp_bill_type_value.setOnItemClickListener { parent, arg1, position, id ->
            billType = codeDesOptions[position].code.toString()

            if (billType == "DWASA") {
                customer_code_input.hint = "Enter Bill Number"
            } else if (billType == "CWASA") {
                customer_code_input.hint = "Enter Account No."
            } else if (billType == "KWASA") {
                customer_code_input.hint = "Enter Customer Number"
            } else if (billType == "RWASA") {
                customer_code_input.hint = "Enter Bill Number"
            }
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


    }
}