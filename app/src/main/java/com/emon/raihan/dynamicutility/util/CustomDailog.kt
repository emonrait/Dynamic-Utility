package com.emon.raihan.dynamicutility.util

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import com.emon.raihan.dynamicutility.R
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import java.util.*

class CustomDailog {


    companion object {
        private val months = arrayOf(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )

        fun createYearPicker(activity: Activity, setYear: MaterialAutoCompleteTextView) {
            lateinit var dateValue: TextView
            lateinit var dayPicker: NumberPicker
            lateinit var monthPicker: NumberPicker
            lateinit var yearPicker: NumberPicker
            val dialog = AlertDialog.Builder(activity).setCancelable(false)
            val inflater = LayoutInflater.from(activity)
            val regLayout = inflater.inflate(R.layout.dialog_year_picker, null)
            //val dateValue = reg_layout.findViewById<TextView>(R.id.dateValue)
            dateValue = regLayout.findViewById(R.id.dateValue)
            yearPicker = regLayout.findViewById(R.id.yearPicker)
            val btnOk = regLayout.findViewById<Button>(R.id.btn_ok)
            val btnCancel = regLayout.findViewById<Button>(R.id.btn_cancel)
            dialog.setView(regLayout)
            val alertDialog = dialog.create()
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val month = cal.get(Calendar.MONTH) + 1



            yearPicker.minValue = 1950
            yearPicker.maxValue = year
            yearPicker.value = year
            yearPicker.wrapSelectorWheel = false
            // yearPicker.setOnValueChangedListener(activity)

            btnOk.setOnClickListener {
                setYear.setText(yearPicker.value.toString())

                alertDialog.dismiss()
            }

            btnCancel.setOnClickListener { alertDialog.dismiss() }


            alertDialog.show()
        }

        fun createMonthPicker(activity: Activity, setYear: MaterialAutoCompleteTextView) {
            lateinit var dateValue: TextView
            lateinit var dayPicker: NumberPicker
            lateinit var monthPicker: NumberPicker
            //   lateinit var yearPicker: NumberPicker
            val dialog = AlertDialog.Builder(activity).setCancelable(false)
            val inflater = LayoutInflater.from(activity)
            val regLayout = inflater.inflate(R.layout.dialog_year_picker, null)
            //val dateValue = reg_layout.findViewById<TextView>(R.id.dateValue)
            dateValue = regLayout.findViewById(R.id.dateValue)
            monthPicker = regLayout.findViewById(R.id.yearPicker)
            val btnOk = regLayout.findViewById<Button>(R.id.btn_ok)
            val btnCancel = regLayout.findViewById<Button>(R.id.btn_cancel)
            dialog.setView(regLayout)
            val alertDialog = dialog.create()
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dateValue.setText(R.string.select_month)
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val month = cal.get(Calendar.MONTH) + 1



            monthPicker.minValue = 1
            monthPicker.maxValue = 12
            monthPicker.value = month
            monthPicker.displayedValues = months



            btnOk.setOnClickListener {
                val newmonth: String = if (monthPicker.value < 10) {
                    "0" + (monthPicker.value).toString()
                } else {
                    monthPicker.value.toString()
                }
                setYear.setText(newmonth)

                alertDialog.dismiss()
            }

            btnCancel.setOnClickListener { alertDialog.dismiss() }


            alertDialog.show()
        }

    }
}