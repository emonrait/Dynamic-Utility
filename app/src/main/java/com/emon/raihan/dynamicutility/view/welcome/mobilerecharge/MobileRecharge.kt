package com.emon.raihan.dynamicutility.view.welcome.mobilerecharge

import android.content.Intent
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
import com.emon.raihan.dynamicutility.view.welcome.Welcome
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout

class MobileRecharge : CustomAppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView

    private lateinit var account_input: TextInputLayout
    private lateinit var sp_account_value: MaterialAutoCompleteTextView
    private lateinit var layout_balance: LinearLayout

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var accountNo = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_recharge)

        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)
        account_input = findViewById(R.id.account_input)
        sp_account_value = findViewById(R.id.sp_account_value)
        layout_balance = findViewById(R.id.layout_balance)

        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.topup)

        iv_header_back.setOnClickListener {
            val intent = Intent(this, Welcome::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        account_input.bringToFront()

        codeDesOptions.add(CodeDesOptions("Select Account", ""))
        codeDesOptions.add(CodeDesOptions("04934008646", "04934008646"))
        codeDesOptions.add(CodeDesOptions("04934008647", "04934008647"))
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, codeDesOptions)
        sp_account_value.setAdapter(arrayAdapter)

        sp_account_value.setOnItemClickListener { parent, arg1, position, id ->
            if (position > 0) {
                accountNo = codeDesOptions[position].code.toString()
                layout_balance.visibility = View.VISIBLE

            }
        }
    }
}