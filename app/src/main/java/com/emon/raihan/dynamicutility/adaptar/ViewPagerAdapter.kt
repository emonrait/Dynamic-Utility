package com.emon.raihan.dynamicutility.adaptar


import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.emon.raihan.dynamicutility.view.loan.fragment.LoanAllFragment
import com.emon.raihan.dynamicutility.view.loan.fragment.LoanApprovedFragment
import com.emon.raihan.dynamicutility.view.loan.fragment.LoanRejectedFragment


class ViewPagerAdapter(activity: FragmentActivity?) : FragmentStateAdapter(activity!!) {
    private val mFragmentList: MutableList<Fragment> = ArrayList()
    private val mFragmentTitleList: MutableList<String> = ArrayList()

    public fun getTabTitle(position: Int): String {
        return mFragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getItemCount(): Int {
        return mFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {

        return mFragmentList[position]
    }
}