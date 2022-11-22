package com.emon.raihan.dynamicutility.view.loan.fragment

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


class LoanRejectedFragment : Fragment() {

    private lateinit var mAdapter: LoanListAdaptar
    private lateinit var reject_loan_recyclerview: RecyclerView
    var loanList: ArrayList<Loan> = ArrayList<Loan>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_loan_rejected, container, false)

        reject_loan_recyclerview = view.findViewById(R.id.reject_loan_recyclerview)

        loanList.clear()
        loanList.add(Loan("30,000. BDT", "23/11/2022 at 10.24 PM", "Personal Loan", "R"))
        loanList.add(Loan("40,000. BDT", "24/11/2022 at 10.37 AM", "Personal Loan", "R"))


        val mLayoutManager = LinearLayoutManager(activity)
        reject_loan_recyclerview.layoutManager = mLayoutManager
        reject_loan_recyclerview.itemAnimator = DefaultItemAnimator()

        mAdapter =
            LoanListAdaptar(
                loanList,

                object : LoanListAdaptar.OnItemClickListener {
                    override fun onItemClick(item: Loan) {

                    }
                })

        reject_loan_recyclerview.adapter = mAdapter
        mAdapter?.notifyDataSetChanged()




        return view
    }


}