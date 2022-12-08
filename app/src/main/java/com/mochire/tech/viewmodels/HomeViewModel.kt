package com.mochire.tech.viewmodels

import androidx.lifecycle.ViewModel
import com.mochire.tech.api.ApiClient

class HomeViewModel : ViewModel() {

        private val api = ApiClient().getApi()
        val symptomName_symptomId = HashMap<String, String>()

        suspend fun loadSymptoms(): HashMap<String, String> {
            val response = api.getSymptoms(25)
            for (symptom in response) {
                symptomName_symptomId[symptom.name] = symptom.id
            }
            return symptomName_symptomId
        }
}