package com.example.tz_login.data.model

data class LoginResponse(
    val success: Boolean,
    val code: Int,
    val message: String,
    val sessionId: String?
)