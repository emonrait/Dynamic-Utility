package com.emon.raihan.dynamicutility.adaptar


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.emon.raihan.dynamicutility.view.loan.fragment.LoanAllFragment
import com.emon.raihan.dynamicutility.view.loan.fragment.LoanApprovedFragment
import com.emon.raihan.dynamicutility.view.loan.fragment.LoanRejectedFragment


class ViewPagerAdapter(var context: Context,
                       fm: FragmentManager,
                       var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                LoanAllFragment()
            }
            1 -> {
                LoanApprovedFragment()
            }
            2 -> {
                LoanRejectedFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}