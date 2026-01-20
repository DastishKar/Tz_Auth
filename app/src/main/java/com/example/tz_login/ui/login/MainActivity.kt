package com.example.tz_login.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tz_login.databinding.ActivityMainBinding
import com.example.tz_login.ui.exit.ExitActivity
import com.example.tz_login.utils.UiState

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        val loginButton = binding.loginButton

        loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.login(username, password)

        }

        viewModel.loginState.observe(this) { state ->
            when (state) {
                is UiState.Loading -> loginButton.isEnabled = false
                is UiState.Success -> {
                    loginButton.isEnabled = true
                    state.data.sessionId?.let {
                        getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                            .edit().putString("session_id", it).apply()
                    }
                    startActivity(Intent(this, ExitActivity::class.java))
                    finish()

                }

                is UiState.Error -> {
                    loginButton.isEnabled = true
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}