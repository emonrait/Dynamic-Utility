package com.emon.raihan.dynamicutility.view.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.util.CustomActivityClear
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import com.emon.raihan.dynamicutility.view.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Welcome : CustomAppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var fab_QR: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        fab_QR = findViewById(R.id.fab_QR)
        navController = findNavController(R.id.nav_host_fragment)


        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false

        fab_QR.setOnClickListener {
            Toast.makeText(this, "QR Pay Button Test", Toast.LENGTH_SHORT).show()
        }

        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_home)

                    return@setOnItemSelectedListener true
                }
                R.id.navigation_statement -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_statement)

                    return@setOnItemSelectedListener true
                }
                R.id.navigation_beneficiary -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_beneficiary)

                    return@setOnItemSelectedListener true
                }
                R.id.navigation_menu -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_menu)

                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false

            }

        }

        this.onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                    val intent = Intent(this@Welcome, MainActivity::class.java)
                    CustomActivityClear.doClearActivity(intent, this@Welcome)


                }
            }
            )


    }


}