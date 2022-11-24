package com.mochire.tech.api

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private const val BASE_URL = "https://api.infermedica.com/v3/"
        private const val API_ID = "9c1e4d31"
        private const val API_KEY = "208c29c95beffaa7d1125af675f6c36d"
    }

    private var retrofit: Retrofit? = null

    fun getApi(): Api {
        if (retrofit == null){
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original: Request = chain.request().newBuilder()
                        .addHeader("App-Id", API_ID)
                        .addHeader("App-Key", API_KEY)
                        .build()

                    chain.proceed(original)
                }
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit?.create(Api::class.java) ?: throw Exception("Retrofit is null")
    }
}
