package com.example.my_app.activity

import AuthHelper.Companion.authHelper
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.my_app.R
import com.example.my_app.databinding.ActivityHomeBinding
import com.example.my_app.databinding.ActivityLoginBinding
import com.example.my_app.databinding.ActivityRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)


        binding.imageView.setOnClickListener{
            authHelper.logOut()
//            Firebase.auth.signOut()
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.userNameId.text = authHelper.auth.currentUser?.displayName
        binding.emailId.text = authHelper.auth.currentUser?.email


    }
}