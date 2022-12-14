package com.emon.raihan.dynamicutility.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity

class AboutMe : CustomAppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView


    private lateinit var gitlink: TextView
    private lateinit var fblink: TextView
    private lateinit var link: TextView
    private lateinit var user_email: TextView
    private lateinit var user_mobile2: TextView
    private lateinit var user_mobile: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)

        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)


        fblink = findViewById(R.id.fblink)
        link = findViewById(R.id.link)
        gitlink = findViewById(R.id.gitlink)
        user_email = findViewById(R.id.user_email)
        user_mobile2 = findViewById(R.id.user_mobile2)
        user_mobile = findViewById(R.id.user_mobile)

        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.about_me)

        iv_header_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        link.setOnClickListener { goToUrl("http://www.emonra.blogspot.com", this@AboutMe) }

        fblink.setOnClickListener {
            goToUrl(
                "http://www.facebook.com/emon.raihan",
                this@AboutMe
            )
        }

        gitlink.setOnClickListener {
            goToUrl(
                "https://github.com/emonrait",
                this@AboutMe
            )
        }

        user_mobile.setOnClickListener {
            if (!checkPermission()) {
                requestPermission()
            } else {
                val callintetnt = Intent(Intent.ACTION_CALL)
                callintetnt.data =
                    Uri.parse("tel:" + user_mobile.getText().toString().trim { it <= ' ' })
                startActivity(callintetnt)
            }
        }

        user_mobile2.setOnClickListener {
            if (!checkPermission()) {
                requestPermission()
            } else {
                val callintetnt = Intent(Intent.ACTION_CALL)
                callintetnt.data =
                    Uri.parse("tel:" + user_mobile2.getText().toString().trim { it <= ' ' })
                startActivity(callintetnt)
            }
        }

        user_email.setOnClickListener {
            val emailIntent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "emonrait@gmail.com", null
                )
            )
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
    }

    private fun checkPermission(): Boolean {
        val var10000: Boolean
        val result =
            ContextCompat.checkSelfPermission((this as Context), "android.permission.CALL_PHONE")
        var10000 = result == 0
        return var10000
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this@AboutMe, arrayOf(
                Manifest.permission.CALL_PHONE
            ), PackageManager.PERMISSION_GRANTED
        )
    }

    fun goToUrl(url: String, activity: Activity) {
        val uriUrl = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        activity.startActivity(launchBrowser)
    }
}
