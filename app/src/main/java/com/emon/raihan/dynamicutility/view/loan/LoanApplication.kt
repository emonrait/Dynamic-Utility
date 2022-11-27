package com.emon.raihan.dynamicutility.view.loan

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
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
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView

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
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)

        sp_applied_for_value = findViewById(R.id.sp_applied_for_value)
        sp_receiving_ac_value = findViewById(R.id.sp_receiving_ac_value)


        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.apply_loan)

        iv_header_back.setOnClickListener {
            val intent = Intent(this, LoanDashboard::class.java)
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