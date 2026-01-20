package com.example.tz_login.network

import com.example.tz_login.data.model.LoginRequest
import com.example.tz_login.data.model.LoginResponse
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): retrofit2.Response<LoginResponse>

}