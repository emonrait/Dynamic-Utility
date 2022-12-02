package com.emon.raihan.dynamicutility.view.welcome.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.model.CodeDesOptions
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.CustomDailog
import com.emon.raihan.dynamicutility.util.DateUtil
import com.emon.raihan.dynamicutility.view.welcome.Welcome
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText


class Statement : Fragment() {
    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView

    private lateinit var sp_account_value: MaterialAutoCompleteTextView
    private lateinit var et_from_date: TextInputEditText
    private lateinit var et_to_date: TextInputEditText
    private lateinit var btnSearch: Button
    private lateinit var btn_today: Button
    private lateinit var btnLastWeek: Button
    private lateinit var btnLastMonth: Button
    private lateinit var statetment_param_layout: LinearLayout

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var accountNo = ""
    var fromDate = ""
    var toDate = ""
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_statement, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)

        sp_account_value = view.findViewById(R.id.sp_account_value)
        et_from_date = view.findViewById(R.id.et_from_date)
        et_to_date = view.findViewById(R.id.et_to_date)
        btnSearch = view.findViewById(R.id.btnSearch)
        btn_today = view.findViewById(R.id.btn_today)
        btnLastWeek = view.findViewById(R.id.btnLastWeek)
        btnLastMonth = view.findViewById(R.id.btnLastMonth)
        statetment_param_layout = view.findViewById(R.id.statetment_param_layout)


        toolbar_title.text = getString(R.string.statement)

        iv_header_back.setOnClickListener {
            val intent = Intent(requireActivity(), Welcome::class.java)
            CustomActivityClear.doClearActivity(intent, requireActivity())

        }
        CustomDailog.verifyStoragePermissions(requireActivity())
        CustomDailog.requestPermission(requireActivity())

        codeDesOptions.add(CodeDesOptions("Select Account", ""))
        codeDesOptions.add(CodeDesOptions("04934008646", "04934008646"))
        codeDesOptions.add(CodeDesOptions("04934008647", "04934008647"))
        val arrayAdapter = ArrayAdapter(requireActivity(), R.layout.dropdown_item, codeDesOptions)
        sp_account_value.setAdapter(arrayAdapter)

        sp_account_value.setOnItemClickListener { parent, arg1, position, id ->
            if (position > 0) {
                accountNo = codeDesOptions[position].code.toString()
            }
        }

        fromDate = DateUtil.geCurrendDate()
        toDate = DateUtil.geCurrendDate()
        et_from_date.setText(fromDate)
        et_to_date.setText(toDate)


        et_from_date.setOnClickListener { CustomDailog.fromDate(requireActivity(), et_from_date) }
        et_to_date.setOnClickListener { CustomDailog.toDate(requireActivity(), et_to_date) }

        btnLastMonth.setOnClickListener {
            fromDate = DateUtil.getCommission_date(30)
            toDate = DateUtil.geCurrendDate()

            et_from_date.setText(fromDate)
            et_to_date.setText(toDate)

        }

        btnLastWeek.setOnClickListener {
            fromDate = DateUtil.getCommission_date(7)
            toDate = DateUtil.geCurrendDate()

            et_from_date.setText(fromDate)
            et_to_date.setText(toDate)

        }

        btn_today.setOnClickListener {
            fromDate = DateUtil.geCurrendDate()
            toDate = DateUtil.geCurrendDate()

            et_from_date.setText(fromDate)
            et_to_date.setText(toDate)

        }

        btnSearch.setOnClickListener {
            val bitmap = CustomDailog.getBitmapFromView(statetment_param_layout)
            if (CustomDailog.addJpgSignatureToGallery(requireActivity(), bitmap)) {
                Toast.makeText(
                    requireActivity(),
                    "Voucher saved into the Gallery",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    requireActivity(),
                    "Unable to store the Voucher",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        return view
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>, grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_EXTERNAL_STORAGE -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.isEmpty()
                    || grantResults[0] != PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(
                        requireActivity(),
                        "Cannot write images to external storage",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


}