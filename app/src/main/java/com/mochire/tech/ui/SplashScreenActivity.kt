package com.mochire.tech.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.mochire.tech.R
import android.os.Handler
import android.os.Looper
import com.mochire.tech.MainActivity

class SplashScreenActivity: AppCompatActivity() {
    private val splashTimeOut: Long = 5000
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, splashTimeOut)
    }
}