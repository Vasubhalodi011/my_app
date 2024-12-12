package com.example.my_app

import AuthHelper.Companion.authHelper
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.my_app.activity.HomeActivity
import com.example.my_app.activity.LoginActivity
import com.example.my_app.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

lateinit var googleClient: GoogleSignInClient

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        googleClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN)

        Handler().postDelayed(Runnable {

            val intent:Intent


            if(authHelper.auth.currentUser!=null) {
                intent = Intent(this,HomeActivity::class.java)
            } else {
                intent = Intent(this,LoginActivity::class.java)
            }

            startActivity(intent)
            finish()
        }, 3000)

    }
}