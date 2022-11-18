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
import java.util.*

class ElectricityBillPayment : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var sp_bill_type_value: MaterialAutoCompleteTextView
    private lateinit var year_month_layout: LinearLayout
    private lateinit var meter_no_input_cardview: CardView
    private lateinit var input_amount_cardview: CardView
    private lateinit var btn_validate_cardview: CardView
    private lateinit var customer_code_input_cardview: CardView

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var billType = ""
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

        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Electricity Bill Payment"

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            CustomActivityClear.doClearActivity(intent,this)
        }

        btn_validate_cardview.visibility=View.GONE
        year_month_layout.visibility=View.GONE
        meter_no_input_cardview.visibility=View.GONE
        input_amount_cardview.visibility=View.GONE
        customer_code_input_cardview.visibility=View.GONE

        codeDesOptions.add(CodeDesOptions("DESCO Postpaid", "DESC"))
        codeDesOptions.add(CodeDesOptions("DESCO Prepaid", "DESCOPRE"))
        codeDesOptions.add(CodeDesOptions("DPDC Postpaid", "DPDC"))
        codeDesOptions.add(CodeDesOptions("DPDC Prepaid", "DPDCPRE"))
        codeDesOptions.add(CodeDesOptions("NESCO Postpaid", "NESCO"))
        codeDesOptions.add(CodeDesOptions("NESCO Prepaid", "NESCOPRE"))
        codeDesOptions.add(CodeDesOptions("Palli Biddyt Postpaid", "PALLI"))
        codeDesOptions.add(CodeDesOptions("Palli Biddyt Prepaid", "PALLIPRE"))
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, codeDesOptions)
        sp_bill_type_value.setAdapter(arrayAdapter)


        sp_bill_type_value.setOnItemClickListener { parent, arg1, position, id ->
            billType = codeDesOptions[position].code.toString()
            if (billType.endsWith("PRE")) {
                year_month_layout.visibility=View.GONE
                meter_no_input_cardview.visibility=View.VISIBLE
                input_amount_cardview.visibility=View.VISIBLE
                btn_validate_cardview.visibility=View.VISIBLE
                customer_code_input_cardview.visibility=View.VISIBLE

            } else {
                year_month_layout.visibility=View.VISIBLE
                meter_no_input_cardview.visibility=View.GONE
                input_amount_cardview.visibility=View.GONE
                btn_validate_cardview.visibility=View.VISIBLE
                customer_code_input_cardview.visibility=View.VISIBLE

            }

        }
    }
}