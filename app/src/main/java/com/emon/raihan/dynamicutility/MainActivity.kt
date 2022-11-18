package com.emon.raihan.dynamicutility

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.widget.*
import com.emon.raihan.dynamicutility.adaptar.MenuAdapter
import com.emon.raihan.dynamicutility.databinding.ActivityFullscreenBinding
import com.emon.raihan.dynamicutility.model.Menu
import com.emon.raihan.dynamicutility.view.AboutMe
import com.emon.raihan.dynamicutility.view.ElectricityBillPayment
import com.emon.raihan.dynamicutility.view.GASBillPayment
import com.emon.raihan.dynamicutility.view.WASABillPayment
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var menuList: ArrayList<Menu>
    private lateinit var menuGridView: GridView
    var adapter: MenuAdapter? = null
    private lateinit var tv_version_name: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)

        menuGridView = findViewById(R.id.menuGridView)
        tv_version_name = findViewById(R.id.tv_version_name)
        menuList = ArrayList<Menu>()

        // tv_version_name.text = "Version Name:- " + BuildConfig.VERSION_NAME

        menuList.add(Menu("EBP", "Electricity Bill Payment", R.drawable.ic_programmer))
        menuList.add(Menu("WASA", "WASA Bill Payment", R.drawable.ic_programmer))
        menuList.add(Menu("GAS", "GAS Bill Payment", R.drawable.ic_programmer))
        menuList.add(Menu("GAS", "Education Bill Payment", R.drawable.ic_programmer))
        menuList.add(Menu("IN", "Insurance Bill Payment", R.drawable.ic_programmer))
        menuList.add(Menu("IN", "Internet Bill Payment", R.drawable.ic_programmer))
        menuList.add(Menu("EN", "Entertainment Bill Payment", R.drawable.ic_programmer))
        menuList.add(Menu("BR", "Bill Report", R.drawable.ic_programmer))
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
                    "RO" == menu_soft_code.text.toString() -> {

                    }
                    "QR" == menu_soft_code.text.toString() -> {

                    }
                    "ST" == menu_soft_code.text.toString() -> {

                    }
                    "TS" == menu_soft_code.text.toString() -> {
                        //val intent = Intent(this, QRGenerate::class.java)
                        // startActivity(intent)
                        Toast.makeText(this, "Total Summary Coming Soon...", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            }


    }


}