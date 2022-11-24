package com.mochire.tech.api

import com.mochire.tech.models.Conditions
import com.mochire.tech.models.Diagnosis
import com.mochire.tech.models.Patient
import com.mochire.tech.models.Symptoms
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @GET("symptoms")
    suspend fun getSymptoms(@Query("age.value") age: Int): List<Symptoms>

    @GET("conditions")
    suspend fun getConditions(@Query("age.value") age: Int): List<Conditions>

    @POST("diagnosis")
    suspend fun getDiagnosis(@Body patient: Patient): Diagnosis

}