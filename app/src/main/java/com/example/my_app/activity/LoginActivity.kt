package com.example.my_app.activity

import AuthHelper.Companion.authHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.my_app.R
import com.example.my_app.databinding.ActivityLoginBinding
import com.example.my_app.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpId.setOnClickListener{
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener{

            val email = binding.emailId.text.toString()
            val password = binding.passwordId.text.toString()


               GlobalScope.launch {
                  var msg = authHelper.signIn(email,password)

                   if(msg=="Success") {
                       var intent = Intent(this@LoginActivity,HomeActivity::class.java)
                       startActivity(intent)
                       finish()
                   }else {
                       Toast.makeText(this@LoginActivity, msg, Toast.LENGTH_SHORT).show()
                   }
               }

        }

    }
}