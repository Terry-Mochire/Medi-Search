package com.mochire.tech.repository

import com.mochire.tech.api.ApiClient

class ApiRepository {

    private val api = ApiClient().getApi()

    suspend fun getSymptoms(limit: Int) = api.getSymptoms(limit)
}