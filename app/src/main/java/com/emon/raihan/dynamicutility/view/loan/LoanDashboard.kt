package com.emon.raihan.dynamicutility.view.loan

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.adaptar.MenuSubAdapter
import com.emon.raihan.dynamicutility.model.Menu
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import com.emon.raihan.dynamicutility.view.MainActivity

class LoanDashboard : CustomAppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView

    private lateinit var menuList: ArrayList<Menu>
    private lateinit var menuGridView: GridView
    var adapter: MenuSubAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_dashboard)

        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)


        menuGridView = findViewById(R.id.menuGridView)
        menuList = ArrayList<Menu>()

        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.loan_dashboard)
        // Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        //  supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //  supportActionBar!!.title = "About Me"

        iv_header_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        menuList.add(Menu("AL", "Loan Application", R.drawable.electricity_bill))
        menuList.add(Menu("LR", "Loan Result", R.drawable.water_bill))

        adapter = MenuSubAdapter(this, menuList)
        menuGridView.adapter = adapter

        menuGridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val menu_soft_code =
                    view.findViewById<View>(R.id.menu_soft_code) as TextView
                //val menu_name =  view.findViewById<View>(R.id.menu_name) as TextView

                when {
                    "AL" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, LoanApplication::class.java)
                        startActivity(intent)
                    }
                    "LR" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, LoanResult::class.java)
                        startActivity(intent)
                    }
                }
            }


    }
}