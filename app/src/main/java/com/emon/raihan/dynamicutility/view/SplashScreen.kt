package com.emon.raihan.dynamicutility.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.util.CustomAppCompatActivity
import java.util.*

@SuppressLint("CustomSplashScreen")
class SplashScreen : CustomAppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private lateinit var timer: Timer
    private lateinit var iv_logo: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        iv_logo = findViewById(R.id.iv_logo)

        iv_logo.animate().rotation(180f).setDuration(2000).start()



        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            timer.cancel()
            val intent = Intent(this, QuickReach::class.java)
            startActivity(intent)
            finish()
        }

        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, 2000, 2000)
    }
}