package com.emon.raihan.dynamicutility.view.welcome.binimoy

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity

class DirectPay : CustomAppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_direct_pay)

        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)

        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.direct_pay)


        iv_header_back.setOnClickListener {
            val intent = Intent(this, BinimoyDashboard::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }


    }
}