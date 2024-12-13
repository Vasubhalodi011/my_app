package com.example.my_app.helper

import AuthHelper.Companion.authHelper
import com.example.my_app.models.TodoModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class FireStoreHelper {

    companion object {
        val fireStoreHelper = FireStoreHelper()
    }

    val fireStore = Firebase.firestore

    // TODO : Title and Description add
    fun addTodo(model: TodoModel) {



        fireStore.collection("MyTodo").document(authHelper.auth.currentUser!!.email.toString())
            .collection("notes").add(model)
    }


    // TODO : Title and Description Fetch
    // TODO : Title and Description Update
    // TODO : Title and Description Delete
}