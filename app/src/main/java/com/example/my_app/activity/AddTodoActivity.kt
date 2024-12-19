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

       val uid =  intent.getStringExtra("uid")
       val updateTitle =  intent.getStringExtra("title")
       val updateDesc =  intent.getStringExtra("desc")

        if(uid!=null) {
            binding.titleId.setText(updateTitle)
            binding.descId.setText(updateDesc)
        }


        binding.saveTodoId.setOnClickListener {
            val title = binding.titleId.text.toString()
            val desc = binding.descId.text.toString()

            if (title.isNotEmpty() && desc.isNotEmpty()) {

                if(uid!=null) {
                    // Update TodoData
                    fireStoreHelper.updateTodo(TodoModel(uid,title,desc))
                }else {
                    // Add TodoData
                    fireStoreHelper.addTodo(TodoModel(null, title, desc))
                }
                finish()
            } else {
                Toast.makeText(this, "Required title and description", Toast.LENGTH_SHORT).show()
            }
        }

    }
}