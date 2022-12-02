package com.emon.raihan.dynamicutility.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.adaptar.LoanListAdaptar
import com.emon.raihan.dynamicutility.model.Loan

import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import com.emon.raihan.dynamicutility.view.utility.InternetBillPayment
import com.emon.raihan.dynamicutility.view.welcome.Welcome


class QuickReach : CustomAppCompatActivity() {
    private lateinit var btn_register: AppCompatButton
    private lateinit var btn_login: AppCompatButton
    private lateinit var poular_RecyclerView: RecyclerView
    private lateinit var mAdapter: LoanListAdaptar
    var loanList: ArrayList<Loan> = ArrayList<Loan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_reach)
        btn_register = findViewById(R.id.btn_register)
        btn_login = findViewById(R.id.btn_login)
        poular_RecyclerView = findViewById(R.id.poular_RecyclerView)

        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)
        val imageList = ArrayList<SlideModel>()

        imageList.add(
            SlideModel(
                "https://images.immediate.co.uk/production/volatile/sites/3/2019/04/Avengers-Endgame-Banner-2-de7cf60.jpg?quality=90&resize=620,413",
                "Avengers Endgame"
            )
        )
        imageList.add(
            SlideModel(
                "https://img.cinemablend.com/filter:scale/quill/3/7/0/0/8/e/37008e36e98cd75101cf1347396eac8534871a19.jpg?mw=600",
                "Jumanji"
            )
        )
        imageList.add(
            SlideModel(
                "https://www.adgully.com/img/800/201711/spider-man-homecoming-banner.jpg",
                "Spider Man"
            )
        )
        imageList.add(
            SlideModel(
                "https://live.staticflickr.com/1980/29996141587_7886795726_b.jpg",
                "Venom"
            )
        )

        imageSlider.setImageList(imageList, ScaleTypes.FIT)

        btn_register.setOnClickListener {
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
        }

        loanList.clear()
        loanList.add(Loan("20,000.00 BDT", "22/11/2022 at 09.58 AM", "Personal Loan", "A"))
        loanList.add(Loan("30,000.00 BDT", "23/11/2022 at 10.24 PM", "Personal Loan", "R"))
        loanList.add(Loan("40,000.00 BDT", "24/11/2022 at 10.37 AM", "Personal Loan", "R"))
        loanList.add(Loan("50,000.00 BDT", "25/11/2022 at 11.40 PM", "Personal Loan", "A"))


        val mLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, true)
        poular_RecyclerView.layoutManager = mLayoutManager
        poular_RecyclerView.itemAnimator = DefaultItemAnimator()

        mAdapter =
            LoanListAdaptar(
                loanList,
                object : LoanListAdaptar.OnItemClickListener {
                    override fun onItemClick(item: Loan) {

                    }
                })

        poular_RecyclerView.adapter = mAdapter
        mAdapter.notifyDataSetChanged()


    }

}