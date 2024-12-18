package com.example.my_app.activity

import AuthHelper.Companion.authHelper
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.my_app.adpater.TodoAdpater
import com.example.my_app.databinding.ActivityHomeBinding
import com.example.my_app.helper.FireStoreHelper.Companion.fireStoreHelper
import com.example.my_app.models.TodoModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)


        fireStoreHelper.fetchTodoData(binding.rvData)


        binding.imageView.setOnClickListener{
            authHelper.logOut()
            googleClient.signOut()
//            Firebase.auth.signOut()
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.userNameId.text = authHelper.auth.currentUser?.displayName
        binding.emailId.text = authHelper.auth.currentUser?.email

        binding.todoAddBtn.setOnClickListener {
            val intent = Intent(this,AddTodoActivity::class.java)
            startActivity(intent)
        }
    }
}