package com.example.my_app.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.my_app.databinding.ActivityAddTodoBinding
import com.example.my_app.helper.FireStoreHelper.Companion.fireStoreHelper
import com.example.my_app.models.TodoModel

class AddTodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTodoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        binding.saveTodoId.setOnClickListener {
            val title = binding.titleId.text.toString()
            val desc = binding.descId.text.toString()

            if (title.isNotEmpty() && desc.isNotEmpty()) {

                fireStoreHelper.addTodo(TodoModel(title, desc))
                finish()
            } else {
                Toast.makeText(this, "Required title and description", Toast.LENGTH_SHORT).show()
            }
        }

    }
}