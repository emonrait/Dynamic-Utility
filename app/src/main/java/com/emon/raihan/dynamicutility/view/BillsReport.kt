package com.emon.raihan.dynamicutility.view

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.media.MediaScannerConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.model.CodeDesOptions
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.DateUtil
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class BillsReport : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var sp_bill_type_value: MaterialAutoCompleteTextView
    private lateinit var et_from_date: TextInputEditText
    private lateinit var et_to_date: TextInputEditText
    private lateinit var btnSearch: Button
    private lateinit var btn_today: Button
    private lateinit var btnLastWeek: Button
    private lateinit var btnLastMonth: Button
    private lateinit var peram_layout: LinearLayout

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var billType = ""
    var fromDate = ""
    var toDate = ""
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bills_report)

        toolbar = findViewById(R.id.toolbar)
        sp_bill_type_value = findViewById(R.id.sp_bill_type_value)
        et_from_date = findViewById(R.id.et_from_date)
        et_to_date = findViewById(R.id.et_to_date)
        btnSearch = findViewById(R.id.btnSearch)
        btn_today = findViewById(R.id.btn_today)
        btnLastWeek = findViewById(R.id.btnLastWeek)
        btnLastMonth = findViewById(R.id.btnLastMonth)
        peram_layout = findViewById(R.id.peram_layout)

        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Bill Payment Report"

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        verifyStoragePermissions(this)
        requestPermission()

        codeDesOptions.add(CodeDesOptions("Dhaka Wasa", "DWASA"))
        codeDesOptions.add(CodeDesOptions("Chattagram Wasa", "CWASA"))
        codeDesOptions.add(CodeDesOptions("Khulna Wasa", "KWASA"))
        codeDesOptions.add(CodeDesOptions("Rajshahi Wasa", "RWASA"))
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, codeDesOptions)
        sp_bill_type_value.setAdapter(arrayAdapter)


        fromDate = DateUtil.geCurrendDate()
        toDate = DateUtil.geCurrendDate()
        et_from_date.setText(fromDate)
        et_to_date.setText(toDate)

        et_from_date.setOnClickListener { fromDate() }
        et_to_date.setOnClickListener { toDate() }

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

        sp_bill_type_value.setOnItemClickListener { parent, arg1, position, id ->
            billType = codeDesOptions[position].code.toString()
        }

        btnSearch.setOnClickListener {
            val bitmap = getBitmapFromView(peram_layout)
            if (bitmap.let { it1 -> addJpgSignatureToGallery(it1) }) {
                Toast.makeText(
                    this@BillsReport,
                    "Voucher saved into the Gallery",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this@BillsReport,
                    "Unable to store the Voucher",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    fun fromDate() {
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
            this,
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


    fun toDate() {
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
            this,
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
                        this@BillsReport,
                        "Cannot write images to external storage",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
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
        val newBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(newBitmap)
        canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        val stream: OutputStream = FileOutputStream(photo)
        newBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        stream.close()
    }

    fun addJpgSignatureToGallery(signature: Bitmap): Boolean {
        var result = false
        try {
            val photo = File(
                getAlbumStorageDir("Utility"),
                String.format("utility_%d.jpg", System.currentTimeMillis())
            )
            saveBitmapToJPG(signature, photo)
            scanMediaFile(photo)
            result = true
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    private fun scanMediaFile(photo: File) {
        /* val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
          val contentUri = Uri.fromFile(photo)
          mediaScanIntent.data = contentUri
          this@QRGenerateActivity.sendBroadcast(mediaScanIntent)*/

        val scan = MediaScannerConnection.scanFile(
            this, arrayOf(photo.toString()),
            null, null
        )

        MediaScannerConnection.scanFile(
            applicationContext, arrayOf(photo.absolutePath),
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

    fun requestPermission() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


                val READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )

                val WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )

                val CAMERA = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                )



                if (READ_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED
                    || WRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED
                    || CAMERA != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this, arrayOf(
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