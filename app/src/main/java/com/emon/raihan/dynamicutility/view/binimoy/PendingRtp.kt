package com.emon.raihan.dynamicutility.view.binimoy

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.adaptar.ViewPagerAdapter
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.view.binimoy.fargment.PendingRTP
import com.emon.raihan.dynamicutility.view.binimoy.fargment.RtpHistory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PendingRtp : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_rtp)

        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)
        tabLayout = findViewById(R.id.tablayout)
        viewPager = findViewById(R.id.viewPager)

        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.pending_rtp)


        iv_header_back.setOnClickListener {
            val intent = Intent(this, BinimoyDashboard::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = ViewPagerAdapter(this)
        adapter.addFragment(PendingRTP(), "Pending RTP")
        adapter.addFragment(RtpHistory(), "RTP history")

        viewPager.adapter = adapter
        viewPager.currentItem = 0



        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getTabTitle(position)
            viewPager.currentItem = tab.position
        }.attach()


    }
}