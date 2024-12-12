package com.example.my_app.activity

import AuthHelper.Companion.authHelper
import android.os.Bundle
import android.widget.Toast
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
            val cpassword = binding.confirmPasswordId.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && cpassword.isNotEmpty()) {
                if(password != cpassword) {
                    Toast.makeText(this, "password and conform password not match", Toast.LENGTH_SHORT).show()
                } else {
                    authHelper.signUp(email, password)
                    finish()
                }
            }

            else {
                Toast.makeText(this, "Required email and password", Toast.LENGTH_SHORT).show()
            }

        }


    }
}