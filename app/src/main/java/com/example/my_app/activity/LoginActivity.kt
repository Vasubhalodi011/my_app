package com.example.my_app.activity

import AuthHelper.Companion.authHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.my_app.R
import com.example.my_app.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Google Sign In

        val googleSignOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id)).requestEmail().build()

        val googleClient = GoogleSignIn.getClient(this, googleSignOption)

        val registerGoogle = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val googleId = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            val credential = GoogleAuthProvider.getCredential(googleId.result.idToken, null)

            authHelper.auth.signInWithCredential(credential).addOnSuccessListener {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
                .addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

        }

        binding.signUpId.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {

            val email = binding.emailId.text.toString()
            val password = binding.passwordId.text.toString()


            GlobalScope.launch {
                var msg = authHelper.signIn(email, password)

                if (msg == "Success") {
                    var intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, msg, Toast.LENGTH_SHORT).show()
                }
            }
        }


        binding.googleBtn.setOnClickListener {
            val intent = googleClient.signInIntent
            registerGoogle.launch(intent)
        }

    }


}