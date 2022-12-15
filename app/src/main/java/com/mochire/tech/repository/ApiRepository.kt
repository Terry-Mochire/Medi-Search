package com.mochire.tech.repository

import com.mochire.tech.api.ApiClient
import com.mochire.tech.models.Conditions
import com.mochire.tech.models.Diagnosis
import com.mochire.tech.models.Patient
import com.mochire.tech.models.Symptoms

class ApiRepository {

    private val api = ApiClient().getApi()
    lateinit var allSymptoms: List <Symptoms>
    lateinit var allDiagnosis: Diagnosis
    lateinit var diagnosisQuestion: String
    lateinit var options: HashMap <String, String>
    lateinit var recommended_specialist: String
    lateinit var allConditions: ArrayList <Conditions>

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
            val choices = HashMap<String, String>()
            allDiagnosis.question.items.forEach {
                choices[it.name] = it.id
            }
            options = choices
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getSpecialist(patient: Patient){
        try {
            val response = api.getSpecialist(patient)
            recommended_specialist = response.recommended_specialist.name
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getConditions(limit: Int) {
        try {
            val response = api.getConditions(limit)
            val conditions = ArrayList<Conditions>()
            response.forEach {
                conditions.add(it)
            }

            allConditions = conditions
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}