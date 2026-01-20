package com.example.tz_login.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tz_login.data.model.LoginRequest
import com.example.tz_login.data.model.LoginResponse
import com.example.tz_login.network.ApiClient
import com.example.tz_login.ui.exit.ExitActivity
import com.example.tz_login.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    val loginState = MutableLiveData<UiState<LoginResponse>>()

    fun login(username: String, password: String) {
        loginState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = ApiClient.authApi.login(LoginRequest(username, password))
                if (response.isSuccessful && response.body() != null) {
                    loginState.value = UiState.Success(response.body()!!)
                }else {
                    loginState.value = UiState.Error("Ошибка: ${response.code()}")
                }

            } catch (e: Exception) {
                loginState.value = UiState.Error(e.localizedMessage ?: "Ошибка сети")
            }


        }


    }
}