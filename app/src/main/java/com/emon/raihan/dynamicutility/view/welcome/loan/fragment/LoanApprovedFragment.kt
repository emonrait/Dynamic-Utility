package com.emon.raihan.dynamicutility.view.welcome.loan.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.adaptar.LoanListAdaptar
import com.emon.raihan.dynamicutility.model.Loan
import java.util.ArrayList


class LoanApprovedFragment : Fragment() {
    private lateinit var mAdapter: LoanListAdaptar
    private lateinit var approved_loan_recyclerview: RecyclerView
    var loanList: ArrayList<Loan> = ArrayList<Loan>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_loan_approved, container, false)

        approved_loan_recyclerview = view.findViewById(R.id.approved_loan_recyclerview)

        loanList.clear()
        loanList.add(Loan("20,000.00 BDT", "22/11/2022 at 09.58 AM", "Personal Loan", "A"))
        loanList.add(Loan("50,000.00 BDT", "25/11/2022 at 11.40 PM", "Personal Loan", "A"))


        val mLayoutManager = LinearLayoutManager(activity)
        approved_loan_recyclerview.layoutManager = mLayoutManager
        approved_loan_recyclerview.itemAnimator = DefaultItemAnimator()

        mAdapter =
            LoanListAdaptar(
                loanList,

                object : LoanListAdaptar.OnItemClickListener {
                    override fun onItemClick(item: Loan) {

                    }
                })

        approved_loan_recyclerview.adapter = mAdapter
        mAdapter.notifyDataSetChanged()



        return view
    }

}