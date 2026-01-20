package com.example.tz_login.ui.exit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tz_login.databinding.ActivityExitBinding
import com.example.tz_login.ui.login.MainActivity

class ExitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sessionId = getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
            .getString("session_id", null)

        binding.textSession.text = sessionId?.let {
            "Session ID:\n$it"
        } ?: "Session отсутствует"

        binding.buttonExit.setOnClickListener {
            getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                .edit().remove("session_id").apply()

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}

