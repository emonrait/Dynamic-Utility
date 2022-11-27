package com.emon.raihan.dynamicutility.view.loan

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.adaptar.ViewPagerAdapter
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import com.emon.raihan.dynamicutility.view.loan.fragment.LoanAllFragment
import com.emon.raihan.dynamicutility.view.loan.fragment.LoanApprovedFragment
import com.emon.raihan.dynamicutility.view.loan.fragment.LoanRejectedFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LoanResult : CustomAppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_result)
        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)

        tabLayout = findViewById(R.id.tablayout)
        viewPager = findViewById(R.id.viewPager)

        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.loan_result)

        iv_header_back.setOnClickListener {
            val intent = Intent(this, LoanDashboard::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = ViewPagerAdapter(this)


        adapter.addFragment(LoanAllFragment(), "All")
        adapter.addFragment(LoanApprovedFragment(), "Approved")
        adapter.addFragment(LoanRejectedFragment(), "Rejected")

        viewPager.adapter = adapter
        viewPager.currentItem = 0

/*
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
*/


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getTabTitle(position)
            viewPager.currentItem = tab.position
        }.attach()

    }
}