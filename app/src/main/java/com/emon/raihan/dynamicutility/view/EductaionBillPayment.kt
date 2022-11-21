package com.emon.raihan.dynamicutility.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.model.CodeDesOptions
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import com.emon.raihan.dynamicutility.util.CustomDailog
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class EductaionBillPayment : CustomAppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView
    private lateinit var iv_bill_type_logo: ImageView
    private lateinit var bill_type_title: TextView
    private lateinit var bill_type_view_layout: LinearLayout

    private lateinit var sp_bill_type_value: MaterialAutoCompleteTextView

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()

    private lateinit var sp_year_value: MaterialAutoCompleteTextView
    private lateinit var sp_month_value: MaterialAutoCompleteTextView
    private lateinit var sp_month_input: TextInputLayout
    private lateinit var sp_year_input: TextInputLayout

    var billType = ""
    var year = ""
    var month = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eductaion_bill_payment)

        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)
        iv_bill_type_logo = findViewById(R.id.iv_bill_type_logo)
        bill_type_title = findViewById(R.id.bill_type_title)
        bill_type_view_layout = findViewById(R.id.bill_type_view_layout)

        sp_bill_type_value = findViewById(R.id.sp_bill_type_value)
        sp_year_value = findViewById(R.id.sp_year_value)
        sp_month_value = findViewById(R.id.sp_month_value)
        sp_month_input = findViewById(R.id.sp_month_input)
        sp_year_input = findViewById(R.id.sp_year_input)

        setSupportActionBar(toolbar)
        toolbar_title.text = "Education Bill Payment"
        // Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        //  supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //  supportActionBar!!.title = "About Me"

        iv_header_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }


        codeDesOptions.add(CodeDesOptions("Dhaka Wasa", "DWASA"))
        codeDesOptions.add(CodeDesOptions("Chattagram Wasa", "CWASA"))
        codeDesOptions.add(CodeDesOptions("Khulna Wasa", "KWASA"))
        codeDesOptions.add(CodeDesOptions("Rajshahi Wasa", "RWASA"))
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, codeDesOptions)
        sp_bill_type_value.setAdapter(arrayAdapter)


        sp_bill_type_value.setOnItemClickListener { parent, arg1, position, id ->
            billType = codeDesOptions[position].code.toString()
            bill_type_title.text = codeDesOptions[position].desc.toString()
            bill_type_view_layout.visibility = View.VISIBLE
            iv_bill_type_logo.setImageResource(R.drawable.water_bill)


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