package com.emon.raihan.dynamicutility.view.binimoy

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.adaptar.Menu3SubAdapter
import com.emon.raihan.dynamicutility.adaptar.MenuSubAdapter
import com.emon.raihan.dynamicutility.model.Menu
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import com.emon.raihan.dynamicutility.util.GlobalVariable
import com.emon.raihan.dynamicutility.view.MainActivity
import com.emon.raihan.dynamicutility.view.loan.LoanApplication
import com.emon.raihan.dynamicutility.view.loan.LoanResult

class BinimoyDashboard : CustomAppCompatActivity() {
    private lateinit var globalVariable: GlobalVariable
    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView

    private lateinit var menuList: ArrayList<Menu>
    private lateinit var menuListProfile: ArrayList<Menu>
    private lateinit var menuListAdditiion: ArrayList<Menu>
    private lateinit var menuGridView: GridView
    private lateinit var menuGridViewProfile: GridView
    private lateinit var menuGridViewAdditional: GridView
    var adapter: Menu3SubAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_DynamicUtility_Custom)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binimoy_dashboard)
        globalVariable = this.applicationContext as GlobalVariable
        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)


        menuGridView = findViewById(R.id.menuGridView)
        menuGridViewProfile = findViewById(R.id.menuGridViewProfile)
        menuGridViewAdditional = findViewById(R.id.menuGridViewAdditional)
        menuList = ArrayList<Menu>()
        menuListProfile = ArrayList<Menu>()
        menuListAdditiion = ArrayList<Menu>()
        toolbar.setBackgroundColor(Color.parseColor(globalVariable.coloreCode))
        setSupportActionBar(toolbar)
        toolbar_title.text = "Binimoy Dashboard"

        iv_header_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        menuList.add(Menu("DP", "Direct Pay", R.drawable.electricity_bill))
        menuList.add(Menu("RTP", "Request To Pay", R.drawable.water_bill))
        menuList.add(Menu("PRTP", "Pending RTP", R.drawable.water_bill))

        menuListProfile.add(Menu("UP", "User Profile", R.drawable.electricity_bill))
        menuListProfile.add(Menu("RTP", "Deafult A/C Set-up", R.drawable.water_bill))
        menuListProfile.add(Menu("PRTP", "Device Registration", R.drawable.water_bill))
        menuListProfile.add(Menu("PRTP", "Pin Management", R.drawable.water_bill))

        menuListAdditiion.add(Menu("DP", "Beneficiary Management", R.drawable.electricity_bill))
        menuListAdditiion.add(Menu("RTP", "Request To Pay", R.drawable.water_bill))
        menuListAdditiion.add(Menu("PRTP", "Pending RTP", R.drawable.water_bill))

        adapter = Menu3SubAdapter(this, menuList)
        menuGridView.adapter = adapter

        menuGridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val menu_soft_code =
                    view.findViewById<View>(R.id.menu_soft_code) as TextView
                val menu_name =
                    view.findViewById<View>(R.id.menu_name) as TextView

                when {
                    "DP" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, DirectPay::class.java)
                        startActivity(intent)
                    }
                    "RTP" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, RequestToPay::class.java)
                        startActivity(intent)
                    }
                    "PRTP" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, PendingRtp::class.java)
                        startActivity(intent)
                    }
                }
            }
        adapter = Menu3SubAdapter(this, menuListProfile)
        menuGridViewProfile.adapter = adapter

        adapter = Menu3SubAdapter(this, menuListAdditiion)
        menuGridViewAdditional.adapter = adapter


    }
}
