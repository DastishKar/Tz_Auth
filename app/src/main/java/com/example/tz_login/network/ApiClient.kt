package com.example.tz_login.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val authApi: AuthApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://bmc-api-dev2.bmcudp.kz/backend/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }
}