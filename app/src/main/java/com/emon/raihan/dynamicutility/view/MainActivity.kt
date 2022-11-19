package com.emon.raihan.dynamicutility.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import com.emon.raihan.dynamicutility.BuildConfig
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.adaptar.MenuAdapter
import com.emon.raihan.dynamicutility.model.Menu
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var menuList: ArrayList<Menu>
    private lateinit var menuGridView: GridView
    var adapter: MenuAdapter? = null
    private lateinit var tv_version_name: TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)

        menuGridView = findViewById(R.id.menuGridView)
        tv_version_name = findViewById(R.id.tv_version_name)
        menuList = ArrayList<Menu>()

         tv_version_name.text = "Version Name:- " + BuildConfig.VERSION_NAME

        menuList.add(Menu("EBP", "Electricity Bill Payment", R.drawable.electricity_bill))
        menuList.add(Menu("WASA", "Water Bill Payment", R.drawable.water_bill))
        menuList.add(Menu("GAS", "GAS Bill Payment", R.drawable.gas_bill))
        menuList.add(Menu("EDU", "Education Bill Payment", R.drawable.education_bill))
        menuList.add(Menu("INS", "Insurance Bill Payment", R.drawable.insurance_bill))
        menuList.add(Menu("IN", "Internet Bill Payment", R.drawable.internet_bill))
        menuList.add(Menu("EN", "Entertainment Bill Payment", R.drawable.entertainment_bill))
        menuList.add(Menu("BR", "Bill Report", R.drawable.invoice))
        menuList.add(Menu("AM", "About Me", R.drawable.ic_programmer))

        adapter = MenuAdapter(this, menuList)
        menuGridView.adapter = adapter

        menuGridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val menu_soft_code =
                    view.findViewById<View>(R.id.menu_soft_code) as TextView
                val menu_name =
                    view.findViewById<View>(R.id.menu_name) as TextView

                when {
                    "EBP" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, ElectricityBillPayment::class.java)
                        startActivity(intent)
                    }
                    "WASA" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, WASABillPayment::class.java)
                        startActivity(intent)
                    }
                    "GAS" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, GASBillPayment::class.java)
                        startActivity(intent)
                    }
                    "AM" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, AboutMe::class.java)
                        startActivity(intent)
                    }
                    "EDU" == menu_soft_code.text.toString() -> {

                        val intent = Intent(this, EductaionBillPayment::class.java)
                        startActivity(intent)
                    }
                    "INS" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, InsuranceBillPayment::class.java)
                        startActivity(intent)
                    }
                    "IN" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, InternetBillPayment::class.java)
                        startActivity(intent)
                    }
                    "EN" == menu_soft_code.text.toString() -> {
                        //val intent = Intent(this, QRGenerate::class.java)
                        // startActivity(intent)
                        Toast.makeText(this, "Total Summary Coming Soon...", Toast.LENGTH_SHORT)
                            .show()
                    }
                    "BR" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, BillsReport::class.java)
                        startActivity(intent)
                        // Toast.makeText(this, "Total Summary Coming Soon...", Toast.LENGTH_SHORT)
                        //     .show()
                    }

                }
            }


    }


}