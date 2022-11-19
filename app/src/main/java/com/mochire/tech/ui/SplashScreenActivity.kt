package com.mochire.tech.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.mochire.tech.R
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.mochire.tech.MainActivity


@Suppress("DEPRECATION")
class SplashScreenActivity: AppCompatActivity() {
    private val splashTimeOut: Long = 5000
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        window.setFlags(
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val splashScreenImage: ImageView = findViewById(R.id.mediSearchBrandGirl)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        splashScreenImage.startAnimation(slideAnimation)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, splashTimeOut)
    }
}