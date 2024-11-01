package com.example.pokeapp.presentation.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokeapp.databinding.ActivityAuthBinding
import com.example.pokeapp.domain.preferences.SharedPreferences
import com.example.pokeapp.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = SharedPreferences(this)
        val loggedIn = sharedPreferences.getData(Boolean, SharedPreferences.LOGGED_IN) as Boolean
        if (loggedIn) {
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }
        binding = ActivityAuthBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}