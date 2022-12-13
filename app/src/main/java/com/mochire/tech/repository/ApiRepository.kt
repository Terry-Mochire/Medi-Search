package com.mochire.tech.repository

import com.mochire.tech.api.ApiClient
import com.mochire.tech.models.Diagnosis
import com.mochire.tech.models.Patient
import com.mochire.tech.models.Symptoms

class ApiRepository {

    private val api = ApiClient().getApi()
    lateinit var allSymptoms: List <Symptoms>
    lateinit var allDiagnosis: Diagnosis
    lateinit var diagnosisQuestion: String
    lateinit var options: ArrayList <String>

    suspend fun getSymptoms(limit: Int) {
        try {
            val response = api.getSymptoms(limit)
            allSymptoms = response
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getDiagnosis(patient: Patient){
        try {
            val response = api.getDiagnosis(patient)
            allDiagnosis = response
            diagnosisQuestion = response.question.text
            val choices = ArrayList <String>()
            allDiagnosis.question.items.forEach() {
                choices.add(it.name)
            }
            options = choices
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}