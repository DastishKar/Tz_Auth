package com.example.tz_login.data.repository

import android.se.omapi.Session

data class AuthRepository (
    val success: Boolean,
    val code: Int,
    val message: String,
    val sessionId: String?
)