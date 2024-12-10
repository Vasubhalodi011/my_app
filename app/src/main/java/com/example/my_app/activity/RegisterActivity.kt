package com.example.my_app.activity

import AuthHelper.Companion.authHelper
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.my_app.R
import com.example.my_app.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)


        binding.signInId.setOnClickListener{
            finish()
        }

        binding.btnRegister.setOnClickListener{

            val email = binding.emailId.text.toString()
            val password = binding.passwordId.text.toString()

            authHelper.signUp(email,password)
            finish()
        }


    }
}