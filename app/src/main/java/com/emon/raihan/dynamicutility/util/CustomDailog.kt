package com.emon.raihan.dynamicutility.util

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.emon.raihan.dynamicutility.R
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
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
        private val REQUEST_EXTERNAL_STORAGE = 1
        private val PERMISSIONS_STORAGE = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        fun createYearPicker(activity: Activity, setYear: MaterialAutoCompleteTextView){
            var value = ""
            lateinit var dateValue: TextView
            lateinit var dayPicker: NumberPicker
            lateinit var monthPicker: NumberPicker
            lateinit var yearPicker: NumberPicker
            lateinit var ivClose: ImageView
            val dialog = AlertDialog.Builder(activity).setCancelable(false)
            val inflater = LayoutInflater.from(activity)
            val regLayout = inflater.inflate(R.layout.dialog_year_picker, null)
            //val dateValue = reg_layout.findViewById<TextView>(R.id.dateValue)
            dateValue = regLayout.findViewById(R.id.dateValue)
            yearPicker = regLayout.findViewById(R.id.yearPicker)
            ivClose = regLayout.findViewById(R.id.ivClose)
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
                value = yearPicker.value.toString()
                alertDialog.dismiss()
            }

            btnCancel.setOnClickListener { alertDialog.dismiss() }

            ivClose.setOnClickListener { alertDialog.dismiss() }


            alertDialog.show()

        }

        fun createMonthPicker(activity: Activity, setYear: MaterialAutoCompleteTextView) {
            var value = ""
            lateinit var dateValue: TextView
            lateinit var monthPicker: NumberPicker
            lateinit var ivClose: ImageView
            val dialog = AlertDialog.Builder(activity).setCancelable(false)
            val inflater = LayoutInflater.from(activity)
            val regLayout = inflater.inflate(R.layout.dialog_year_picker, null)
            dateValue = regLayout.findViewById(R.id.dateValue)
            monthPicker = regLayout.findViewById(R.id.yearPicker)
            ivClose = regLayout.findViewById(R.id.ivClose)
            val btnOk = regLayout.findViewById<Button>(R.id.btn_ok)
            val btnCancel = regLayout.findViewById<Button>(R.id.btn_cancel)
            dialog.setView(regLayout)
            val alertDialog = dialog.create()
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dateValue.setText(R.string.select_month)
            val cal = Calendar.getInstance()
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
                value = newmonth
                alertDialog.dismiss()

            }

            btnCancel.setOnClickListener { alertDialog.dismiss() }
            ivClose.setOnClickListener { alertDialog.dismiss() }



            alertDialog.show()


        }

        fun fromDate(activity: Activity, et_from_date: TextInputEditText) {
            // Get Current Date
            val mYear: Int
            val mMonth: Int
            val mDay: Int
            // Get Current Date
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]


            val datePickerDialog = DatePickerDialog(
                activity,
                { view, year, monthOfYear, dayOfMonth ->
                    val month1: String = if (monthOfYear < 9) {
                        "0" + (monthOfYear + 1)
                    } else {
                        "" + (monthOfYear + 1)
                    }
                    val day1: String = if (view!!.dayOfMonth <= 9) {
                        "0" + view.dayOfMonth
                    } else {
                        "" + view.dayOfMonth
                    }

                    et_from_date.setText("$day1/$month1/$year")
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }


        fun toDate(activity: Activity, et_to_date: TextInputEditText) {
            // Get Current Date
            val mYear: Int
            val mMonth: Int
            val mDay: Int
            // Get Current Date
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]


            val datePickerDialog = DatePickerDialog(
                activity,
                { view, year, monthOfYear, dayOfMonth ->
                    val month1: String = if (monthOfYear < 9) {
                        "0" + (monthOfYear + 1)
                    } else {
                        "" + (monthOfYear + 1)
                    }
                    val day1: String = if (view!!.dayOfMonth <= 9) {
                        "0" + view.dayOfMonth
                    } else {
                        "" + view.dayOfMonth
                    }

                    et_to_date.setText("$day1/$month1/$year")
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }

        fun getBitmapFromView(view: View): Bitmap {
            val bitmap = Bitmap.createBitmap(
                view.width, view.height, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            return bitmap
        }

        fun getBitmapFromView(view: View, defaultColor: Int): Bitmap {
            val bitmap = Bitmap.createBitmap(
                view.width, view.height, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            canvas.drawColor(defaultColor)
            view.draw(canvas)
            return bitmap
        }

        fun getAlbumStorageDir(albumName: String): File {
            // Get the directory for the user's public pictures directory.

            val file = File(
                Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES
                ), albumName
            )
            if (!file.mkdirs()) {
                Log.e("DynamicUtility-->", "Directory not created")
            }
            return file
        }

        @Throws(IOException::class)
        fun saveBitmapToJPG(bitmap: Bitmap, photo: File) {
            val newBitmap =
                Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(newBitmap)
            canvas.drawColor(Color.WHITE)
            canvas.drawBitmap(bitmap, 0f, 0f, null)
            val stream: OutputStream = FileOutputStream(photo)
            newBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()
        }

        fun addJpgSignatureToGallery(activity: Activity, signature: Bitmap): Boolean {
            var result = false
            try {
                val photo = File(
                    getAlbumStorageDir("Utility"),
                    String.format("utility_%d.jpg", System.currentTimeMillis())
                )
                saveBitmapToJPG(signature, photo)
                scanMediaFile(activity, photo)
                result = true
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return result
        }

        private fun scanMediaFile(activity: Activity, photo: File) {
            /* val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
              val contentUri = Uri.fromFile(photo)
              mediaScanIntent.data = contentUri
              this@QRGenerateActivity.sendBroadcast(mediaScanIntent)*/

            val scan = MediaScannerConnection.scanFile(
                activity, arrayOf(photo.toString()),
                null, null
            )

            MediaScannerConnection.scanFile(
                activity, arrayOf(photo.absolutePath),
                null
            ) { path, uri ->
                Log.v(
                    "vslue-->",
                    "file $path was scanned seccessfully: $uri"
                )
            }
        }

        fun verifyStoragePermissions(activity: Activity?) {
            // Check if we have write permission
            val permission = ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
                )
            }
        }

        fun requestPermission(activity: Activity) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


                    val READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )

                    val WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )

                    val CAMERA = ContextCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.CAMERA
                    )



                    if (READ_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED
                        || WRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED
                        || CAMERA != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(
                            activity, arrayOf(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA
                            ), 1
                        )
                    }


                }


            } catch (ex: Exception) {
                Log.e("", ex.message.toString())
            }

        }
    }
}