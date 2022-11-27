package com.emon.raihan.dynamicutility.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.adaptar.DropdownListAdaptar
import com.emon.raihan.dynamicutility.model.CodeDesOptions
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import com.emon.raihan.dynamicutility.util.CustomDailog
import com.emon.raihan.dynamicutility.util.DialogCustom
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class GASBillPayment : CustomAppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView
    private lateinit var iv_bill_type_logo: ImageView
    private lateinit var bill_type_title: TextView
    private lateinit var bill_type_view_layout: LinearLayout

    private lateinit var sp_bill_type_value: MaterialAutoCompleteTextView
    private lateinit var input_value_param_layout: LinearLayout
    private lateinit var year_month_layout: LinearLayout
    private lateinit var customer_code_input_cardview: CardView
    private lateinit var meter_no_input_cardview: CardView
    private lateinit var invoice_no_input_cardview: CardView
    private lateinit var input_mobile_no_cardview: CardView
    private lateinit var customer_code_input: TextInputLayout

    private lateinit var sp_year_value: MaterialAutoCompleteTextView
    private lateinit var sp_month_value: MaterialAutoCompleteTextView
    private lateinit var sp_month_input: TextInputLayout
    private lateinit var sp_year_input: TextInputLayout

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var billType = ""
    var year = ""
    var month = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gasbill_payment)

        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)
        iv_bill_type_logo = findViewById(R.id.iv_bill_type_logo)
        bill_type_title = findViewById(R.id.bill_type_title)
        bill_type_view_layout = findViewById(R.id.bill_type_view_layout)

        sp_bill_type_value = findViewById(R.id.sp_bill_type_value)
        year_month_layout = findViewById(R.id.year_month_layout)
        customer_code_input_cardview = findViewById(R.id.customer_code_input_cardview)
        customer_code_input = findViewById(R.id.customer_code_input)
        meter_no_input_cardview = findViewById(R.id.meter_no_input_cardview)
        invoice_no_input_cardview = findViewById(R.id.invoice_no_input_cardview)
        input_mobile_no_cardview = findViewById(R.id.input_mobile_no_cardview)
        input_value_param_layout = findViewById(R.id.input_value_param_layout)
        sp_year_value = findViewById(R.id.sp_year_value)
        sp_month_value = findViewById(R.id.sp_month_value)
        sp_month_input = findViewById(R.id.sp_month_input)
        sp_year_input = findViewById(R.id.sp_year_input)

        setSupportActionBar(toolbar)
        toolbar_title.text = "Gas Bill Payment"
        // Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        //  supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //  supportActionBar!!.title = "About Me"

        iv_header_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        input_value_param_layout.visibility=View.GONE
        codeDesOptions.add(CodeDesOptions("Titas Gas Postpaid (Non-Metered)", "TGPNM"))
        codeDesOptions.add(CodeDesOptions("Titas Gas Postpaid (Metered)", "TGPM"))
        codeDesOptions.add(CodeDesOptions("Karnaphuli Gas Ltd", "KGL"))
        codeDesOptions.add(CodeDesOptions("Jalalabad Gas Ltd", "JGLS"))
        codeDesOptions.add(CodeDesOptions("Sundarban Gas Ltd", "SGLS"))
        codeDesOptions.add(CodeDesOptions("Paschimanchal Gas Ltd", "PGLS"))
        codeDesOptions.add(CodeDesOptions("Bakhrabad Gas Ltd", "BGL"))
        codeDesOptions.add(CodeDesOptions("Bashundara LP Gas Ltd (Metered)", "BLPGLM"))
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, codeDesOptions)
        sp_bill_type_value.setAdapter(arrayAdapter)


        sp_bill_type_value.setOnItemClickListener { parent, arg1, position, id ->
            billType = codeDesOptions[position].code.toString()
            input_value_param_layout.visibility=View.VISIBLE
            bill_type_title.text = codeDesOptions[position].desc.toString()
            bill_type_view_layout.visibility = View.VISIBLE
            iv_bill_type_logo.setImageResource(R.drawable.gas_bill)

            if (billType.endsWith("GLS")) {
                year_month_layout.visibility = View.VISIBLE
                customer_code_input_cardview.visibility = View.VISIBLE
                meter_no_input_cardview.visibility = View.GONE
                invoice_no_input_cardview.visibility = View.GONE
                input_mobile_no_cardview.visibility = View.GONE
                customer_code_input.hint = "Enter Grahohk Shonket no."
            } else if (billType.equals("KGL")) {
                year_month_layout.visibility = View.GONE
                customer_code_input_cardview.visibility = View.VISIBLE
                meter_no_input_cardview.visibility = View.GONE
                invoice_no_input_cardview.visibility = View.GONE
                input_mobile_no_cardview.visibility = View.VISIBLE
                customer_code_input.hint = "Enter Bill Account Number"
            } else if (billType.equals("TGPM")) {
                year_month_layout.visibility = View.GONE
                customer_code_input_cardview.visibility = View.VISIBLE
                meter_no_input_cardview.visibility = View.GONE
                invoice_no_input_cardview.visibility = View.VISIBLE
                input_mobile_no_cardview.visibility = View.GONE
                customer_code_input.hint = "Enter Customer No"
            } else if (billType.equals("TGPNM")) {
                year_month_layout.visibility = View.VISIBLE
                customer_code_input_cardview.visibility = View.VISIBLE
                meter_no_input_cardview.visibility = View.GONE
                invoice_no_input_cardview.visibility = View.GONE
                input_mobile_no_cardview.visibility = View.VISIBLE
                customer_code_input.hint = "Enter Customer Code"
            } else if(billType.equals("BLPGLM")){
                year_month_layout.visibility = View.GONE
                customer_code_input_cardview.visibility = View.GONE
                meter_no_input_cardview.visibility = View.VISIBLE
                invoice_no_input_cardview.visibility = View.GONE
                input_mobile_no_cardview.visibility = View.GONE
            }


        }

        sp_bill_type_value.setOnClickListener {
            showDropDoownDialog()
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

    @SuppressLint("NotifyDataSetChanged")
    fun showDropDoownDialog() {
        lateinit var et_bill_type_value: TextInputEditText
        lateinit var mAdapter: DropdownListAdaptar
        lateinit var ivClose: ImageView
        lateinit var dropdown_recycler: RecyclerView
        val dialog = AlertDialog.Builder(this).setCancelable(false)
        val inflater = LayoutInflater.from(this)
        val regLayout = inflater.inflate(R.layout.dialog_dropdown_picker, null)
        et_bill_type_value = regLayout.findViewById(R.id.et_bill_type_value)
        dropdown_recycler = regLayout.findViewById(R.id.dropdown_recycler)
        ivClose = regLayout.findViewById(R.id.ivClose)
        dialog.setView(regLayout)
        val alertDialog = dialog.create()
        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val mLayoutManager = LinearLayoutManager(this)
        dropdown_recycler.layoutManager = mLayoutManager
        dropdown_recycler.itemAnimator = DefaultItemAnimator()

        mAdapter =
            DropdownListAdaptar(
                codeDesOptions, dialog,
                object : DropdownListAdaptar.OnItemClickListener {
                    override fun onItemClick(item: CodeDesOptions) {
                        sp_bill_type_value.setText(item.desc)
                        billType = item.code.toString()
                        input_value_param_layout.visibility=View.VISIBLE
                        bill_type_title.text = item.desc.toString()
                        bill_type_view_layout.visibility = View.VISIBLE
                        iv_bill_type_logo.setImageResource(R.drawable.gas_bill)

                        if (billType.endsWith("GLS")) {
                            year_month_layout.visibility = View.VISIBLE
                            customer_code_input_cardview.visibility = View.VISIBLE
                            meter_no_input_cardview.visibility = View.GONE
                            invoice_no_input_cardview.visibility = View.GONE
                            input_mobile_no_cardview.visibility = View.GONE
                            customer_code_input.hint = "Enter Grahohk Shonket no."
                        } else if (billType.equals("KGL")) {
                            year_month_layout.visibility = View.GONE
                            customer_code_input_cardview.visibility = View.VISIBLE
                            meter_no_input_cardview.visibility = View.GONE
                            invoice_no_input_cardview.visibility = View.GONE
                            input_mobile_no_cardview.visibility = View.VISIBLE
                            customer_code_input.hint = "Enter Bill Account Number"
                        } else if (billType.equals("TGPM")) {
                            year_month_layout.visibility = View.GONE
                            customer_code_input_cardview.visibility = View.VISIBLE
                            meter_no_input_cardview.visibility = View.GONE
                            invoice_no_input_cardview.visibility = View.VISIBLE
                            input_mobile_no_cardview.visibility = View.GONE
                            customer_code_input.hint = "Enter Customer No"
                        } else if (billType.equals("TGPNM")) {
                            year_month_layout.visibility = View.VISIBLE
                            customer_code_input_cardview.visibility = View.VISIBLE
                            meter_no_input_cardview.visibility = View.GONE
                            invoice_no_input_cardview.visibility = View.GONE
                            input_mobile_no_cardview.visibility = View.VISIBLE
                            customer_code_input.hint = "Enter Customer Code"
                        } else if(billType.equals("BLPGLM")){
                            year_month_layout.visibility = View.GONE
                            customer_code_input_cardview.visibility = View.GONE
                            meter_no_input_cardview.visibility = View.VISIBLE
                            invoice_no_input_cardview.visibility = View.GONE
                            input_mobile_no_cardview.visibility = View.GONE
                        }

                        alertDialog.dismiss()
                    }
                })

        dropdown_recycler.adapter = mAdapter
        mAdapter.notifyDataSetChanged()

        et_bill_type_value.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                mAdapter.filter.filter(s)
            }
        })

        ivClose.setOnClickListener { alertDialog.dismiss() }


        alertDialog.show()

    }

}