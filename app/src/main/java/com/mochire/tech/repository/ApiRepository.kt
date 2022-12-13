package com.mochire.tech.repository

import com.mochire.tech.api.ApiClient
import com.mochire.tech.models.Diagnosis
import com.mochire.tech.models.Patient
import com.mochire.tech.models.Symptoms

class ApiRepository {

    private val api = ApiClient().getApi()
    lateinit var allSymptoms: List <Symptoms>
    lateinit var allDiagnosis: Diagnosis

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
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}