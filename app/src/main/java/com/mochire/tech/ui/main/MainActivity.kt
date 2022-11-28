package com.mochire.tech.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseUser
import com.mochire.tech.R
import com.mochire.tech.databinding.ActivityMainBinding
import java.util.*


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar2)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main2)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_profile, R.id.navigation_home, R.id.navigation_conditions
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        window.setFlags(
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val account = intent.getStringExtra("account")
        val user = intent.getStringExtra("user")
        Log.d("account", account.toString())
        Log.d("user", user.toString())


//        GlobalScope.launch {
//
//
//            val diagnosis = api.getDiagnosis(
//                Patient(
//                    Age(30),
//                    listOf(Evidence("present", "s_299"), Evidence("present", "s_13")),
//                    sex = "male"
//
//                )
//            )
//
//            Log.d("TAG", "onCreate: $diagnosis")
//        }
    }
}