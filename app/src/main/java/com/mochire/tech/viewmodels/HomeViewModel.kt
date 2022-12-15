package com.mochire.tech.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mochire.tech.models.Age
import com.mochire.tech.models.Evidence
import com.mochire.tech.models.Patient
import com.mochire.tech.repository.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = ApiRepository()

    val allSymptoms = viewModelScope.async {
        repository.getSymptoms(30)
        repository.allSymptoms
    }

    val symptomNameWithId = viewModelScope.async {
        val symptoms = allSymptoms.await()
        val symptomNameWithId = mutableMapOf<String, String>()
        symptoms.forEach {
            symptomNameWithId[it.name] = it.id
        }
        symptomNameWithId
    }

    fun getDiagnosis(symptomId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val patient = Patient(Age(30), listOf(Evidence("present", symptomId)), "male")
            val response = repository.getDiagnosis(patient)
            val diagnosis = repository.allDiagnosis.question.text

            Log.d("diagnosis", diagnosis)
        }
    }
}