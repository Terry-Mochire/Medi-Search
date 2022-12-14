package com.mochire.tech.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mochire.tech.models.Age
import com.mochire.tech.models.Condition
import com.mochire.tech.models.Evidence
import com.mochire.tech.models.Patient
import com.mochire.tech.repository.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = ApiRepository()
    var returnedConditions = listOf<Condition>()
    var data = ArrayList<String>()
    var question = ""
    var submitSymptomId = ""

    private val allSymptoms = viewModelScope.async {
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
        CoroutineScope(Dispatchers.Main.immediate).launch {
            val symptoms = ArrayList<Evidence>()
            if (submitSymptomId != ""){
                symptoms.add(Evidence("present", submitSymptomId))
                symptoms.add(Evidence("present", symptomId))
            } else {
                symptoms.add(Evidence("present", symptomId))
            }
            val patient = Patient(Age(30), symptoms, "male")
            Log.d("patient", patient.toString())
            repository.getDiagnosis(patient)
            question = repository.diagnosisQuestion
            data = repository.options.keys.toCollection(ArrayList())

            val conditions = mutableListOf<Condition>()
            repository.allDiagnosis.conditions.forEach {
                conditions.add(it)
            }

            returnedConditions = conditions
        }
    }

    fun getAssessmentQuestionId(choiceName: String): String {
        val choiceNameWithId = repository.options
        return choiceNameWithId[choiceName]!!
    }
}