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
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import com.emon.raihan.dynamicutility.util.CustomDailog
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class InsuranceBillPayment : CustomAppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var sp_bill_type_value: MaterialAutoCompleteTextView
    private lateinit var input_value_param_layout: LinearLayout
    private lateinit var sp_purpose_cardview: CardView
    private lateinit var policy_number_input_cardview: CardView
    private lateinit var input_mobile_no_cardview: CardView

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var billType = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insurance_bill_payment)
        toolbar = findViewById(R.id.toolbar)
        sp_bill_type_value = findViewById(R.id.sp_bill_type_value)
        input_value_param_layout = findViewById(R.id.input_value_param_layout)
        sp_purpose_cardview = findViewById(R.id.sp_purpose_cardview)
        policy_number_input_cardview = findViewById(R.id.policy_number_input_cardview)
        input_mobile_no_cardview = findViewById(R.id.input_mobile_no_cardview)

        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Insurance Bill Payment"

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }
        input_value_param_layout.visibility = View.GONE

        codeDesOptions.add(CodeDesOptions("Metlife", "METLIFE"))
        codeDesOptions.add(CodeDesOptions("Jibon Bima Corporation", "JBC"))
        codeDesOptions.add(CodeDesOptions("Pragati Life Insurance", "PRAGATI"))
        codeDesOptions.add(CodeDesOptions("Prime Islami Life Insurance", "PIL"))
        codeDesOptions.add(CodeDesOptions("Fareast Islami Life Insurance", "FIL"))
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, codeDesOptions)
        sp_bill_type_value.setAdapter(arrayAdapter)


        sp_bill_type_value.setOnItemClickListener { parent, arg1, position, id ->
            billType = codeDesOptions[position].code.toString()
            input_value_param_layout.visibility = View.VISIBLE

            if (billType.equals("METLIFE")) {
                sp_purpose_cardview.visibility = View.VISIBLE
                policy_number_input_cardview.visibility = View.VISIBLE
                input_mobile_no_cardview.visibility = View.GONE
            } else {
                sp_purpose_cardview.visibility = View.GONE
                policy_number_input_cardview.visibility = View.VISIBLE
                input_mobile_no_cardview.visibility = View.VISIBLE
            }


        }


    }
}