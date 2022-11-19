package com.emon.raihan.dynamicutility.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.model.CodeDesOptions
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class WASABillPayment : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var sp_bill_type_value: MaterialAutoCompleteTextView
    private lateinit var customer_code_input: TextInputLayout

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var billType = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wasabill_payment)

        toolbar = findViewById(R.id.toolbar)
        sp_bill_type_value = findViewById(R.id.sp_bill_type_value)
        customer_code_input = findViewById(R.id.customer_code_input)

        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Water Bill Payment"

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


    }
}