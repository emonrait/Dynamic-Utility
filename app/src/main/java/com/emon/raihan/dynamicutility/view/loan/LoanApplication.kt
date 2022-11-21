package com.emon.raihan.dynamicutility.view.loan

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.model.CodeDesOptions
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import com.emon.raihan.dynamicutility.view.MainActivity
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class LoanApplication : CustomAppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var sp_applied_for_value: MaterialAutoCompleteTextView
    private lateinit var sp_receiving_ac_value: MaterialAutoCompleteTextView

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var accodeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()

    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_application)

        toolbar = findViewById(R.id.toolbar)
        sp_applied_for_value = findViewById(R.id.sp_applied_for_value)
        sp_receiving_ac_value = findViewById(R.id.sp_receiving_ac_value)

        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Apply Loan"

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }


        codeDesOptions.add(CodeDesOptions("Personal Loan", "PL"))
        codeDesOptions.add(CodeDesOptions("Car Loan", "CL"))
        codeDesOptions.add(CodeDesOptions("House Loan", "HL"))
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, codeDesOptions)
        sp_applied_for_value.setAdapter(arrayAdapter)

        accodeDesOptions.add(CodeDesOptions("04934008646", "04934008646"))
        accodeDesOptions.add(CodeDesOptions("04934008647", "04934008647"))
        accodeDesOptions.add(CodeDesOptions("04934008648", "04934008648"))
        val acarrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, accodeDesOptions)
        sp_receiving_ac_value.setAdapter(acarrayAdapter)

    }
}