package com.mochire.tech.ui.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mochire.tech.R

@Suppress("DEPRECATION")
class AuthActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        window.setFlags(
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }
}