package com.emon.raihan.dynamicutility.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.model.CodeDesOptions
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class InternetBillPayment : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var sp_bill_type_value: MaterialAutoCompleteTextView
    private lateinit var year_month_layout: LinearLayout
    private lateinit var input_value_param_layout: LinearLayout
    private lateinit var customer_code_input_cardview: CardView
    private lateinit var input_mobile_no_cardview: CardView
    private lateinit var amount_input_cardview: CardView
    private lateinit var customer_code_input: TextInputLayout

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var billType = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet_bill_payment)

        toolbar = findViewById(R.id.toolbar)
        sp_bill_type_value = findViewById(R.id.sp_bill_type_value)
        year_month_layout = findViewById(R.id.year_month_layout)
        customer_code_input_cardview = findViewById(R.id.customer_code_input_cardview)
        customer_code_input = findViewById(R.id.customer_code_input)
        input_mobile_no_cardview = findViewById(R.id.input_mobile_no_cardview)
        amount_input_cardview = findViewById(R.id.amount_input_cardview)
        input_value_param_layout = findViewById(R.id.input_value_param_layout)

        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Internet Bill Payment"

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        input_value_param_layout.visibility = View.GONE
        codeDesOptions.add(CodeDesOptions("Carnival", "CARNIVALPRE"))
        codeDesOptions.add(CodeDesOptions("Link3", "LINK3PRE"))
        codeDesOptions.add(CodeDesOptions("Amber IT", "AMBERPRE"))
        codeDesOptions.add(CodeDesOptions("Sam Online", "SAMPRE"))
        codeDesOptions.add(CodeDesOptions("KS Network Ltd", "KSNL"))
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, codeDesOptions)
        sp_bill_type_value.setAdapter(arrayAdapter)


        sp_bill_type_value.setOnItemClickListener { parent, arg1, position, id ->
            billType = codeDesOptions[position].code.toString()
            input_value_param_layout.visibility = View.VISIBLE

            if (billType.endsWith("PRE")) {
                year_month_layout.visibility = View.GONE
                customer_code_input_cardview.visibility = View.VISIBLE
                input_mobile_no_cardview.visibility = View.VISIBLE
                amount_input_cardview.visibility = View.VISIBLE
            } else {
                year_month_layout.visibility = View.VISIBLE
                input_mobile_no_cardview.visibility = View.GONE
                amount_input_cardview.visibility = View.GONE
                customer_code_input_cardview.visibility = View.VISIBLE
                customer_code_input.hint = "Enter Bill Account Number"
            }

        }
    }
}