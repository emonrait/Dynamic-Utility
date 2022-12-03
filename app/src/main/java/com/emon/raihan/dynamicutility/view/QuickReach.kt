package com.emon.raihan.dynamicutility.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.adaptar.QuickReachMenuAdaptar
import com.emon.raihan.dynamicutility.model.Menu

import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import com.emon.raihan.dynamicutility.view.welcome.Welcome


class QuickReach : CustomAppCompatActivity() {
    private lateinit var btn_register: AppCompatButton
    private lateinit var btn_login: AppCompatButton
    private lateinit var poular_RecyclerView: RecyclerView
    private lateinit var whatsnew_RecyclerView: RecyclerView
    private lateinit var information_RecyclerView: RecyclerView
    private lateinit var mAdapter: QuickReachMenuAdaptar
    var popularList: ArrayList<Menu> = ArrayList<Menu>()
    var whatsnew_List: ArrayList<Menu> = ArrayList<Menu>()
    var information_List: ArrayList<Menu> = ArrayList<Menu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_reach)
        btn_register = findViewById(R.id.btn_register)
        btn_login = findViewById(R.id.btn_login)
        poular_RecyclerView = findViewById(R.id.poular_RecyclerView)
        whatsnew_RecyclerView = findViewById(R.id.whatsnew_RecyclerView)
        information_RecyclerView = findViewById(R.id.information_RecyclerView)

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

        popularList.clear()
        popularList.add(Menu("WB", "Within Bank", R.drawable.water_bill))
        popularList.add(Menu("NPSB", "NPSB", R.drawable.education_bill))
        popularList.add(Menu("BP", "Bills Pay", R.drawable.gas_bill))
        popularList.add(Menu("bKash", "bKash", R.drawable.electricity_bill))
        popularList.add(Menu("bKash", "bKash", R.drawable.electricity_bill))


        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        poular_RecyclerView.layoutManager = mLayoutManager
        poular_RecyclerView.itemAnimator = DefaultItemAnimator()

        mAdapter =
            QuickReachMenuAdaptar(
                popularList,
                object : QuickReachMenuAdaptar.OnItemClickListener {
                    override fun onItemClick(item: Menu) {
                        Toast.makeText(this@QuickReach, item.menuTitle, Toast.LENGTH_SHORT).show()
                    }
                })

        poular_RecyclerView.adapter = mAdapter
        mAdapter.notifyDataSetChanged()


        whatsnew_List.clear()
        whatsnew_List.add(Menu("WB", "Within Bank", R.drawable.water_bill))
        whatsnew_List.add(Menu("NPSB", "NPSB", R.drawable.education_bill))
        whatsnew_List.add(Menu("BP", "Bills Pay", R.drawable.gas_bill))
        whatsnew_List.add(Menu("bKash", "bKash", R.drawable.electricity_bill))
        whatsnew_List.add(Menu("bKash", "bKash", R.drawable.electricity_bill))


        val mLayoutManagerNew = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        whatsnew_RecyclerView.layoutManager = mLayoutManagerNew
        whatsnew_RecyclerView.itemAnimator = DefaultItemAnimator()

        mAdapter =
            QuickReachMenuAdaptar(
                whatsnew_List,
                object : QuickReachMenuAdaptar.OnItemClickListener {
                    override fun onItemClick(item: Menu) {
                        Toast.makeText(this@QuickReach, item.menuTitle, Toast.LENGTH_SHORT).show()
                    }
                })

        whatsnew_RecyclerView.adapter = mAdapter
        mAdapter.notifyDataSetChanged()



        information_List.clear()
        information_List.add(Menu("WB", "Within Bank", R.drawable.water_bill))
        information_List.add(Menu("NPSB", "NPSB", R.drawable.education_bill))
        information_List.add(Menu("BP", "Bills Pay", R.drawable.gas_bill))
        information_List.add(Menu("bKash", "bKash", R.drawable.electricity_bill))
        information_List.add(Menu("bKash", "bKash", R.drawable.electricity_bill))


        val mLayoutManagerInfo = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        information_RecyclerView.layoutManager = mLayoutManagerInfo
        information_RecyclerView.itemAnimator = DefaultItemAnimator()

        mAdapter =
            QuickReachMenuAdaptar(
                information_List,
                object : QuickReachMenuAdaptar.OnItemClickListener {
                    override fun onItemClick(item: Menu) {
                        Toast.makeText(this@QuickReach, item.menuTitle, Toast.LENGTH_SHORT).show()
                    }
                })

        information_RecyclerView.adapter = mAdapter
        mAdapter.notifyDataSetChanged()

    }

}