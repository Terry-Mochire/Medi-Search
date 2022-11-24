package com.mochire.tech

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mochire.tech.api.ApiClient
import com.mochire.tech.models.Age
import com.mochire.tech.models.Evidence
import com.mochire.tech.models.Patient
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = ApiClient().getApi()

       GlobalScope.launch {
//            val symptoms = api.getSymptoms(20)
//            if (symptoms != null){
//                Log.d("TAG", "onCreate: ${symptoms.size}")
//            }

            val diagnosis = api.getDiagnosis(
                Patient(
                    Age(30),
                    listOf(Evidence("present", "s_299"), Evidence("present", "s_13")),
                    sex = "male"

            ))

            Log.d("TAG", "onCreate: $diagnosis")
       }
    }
}