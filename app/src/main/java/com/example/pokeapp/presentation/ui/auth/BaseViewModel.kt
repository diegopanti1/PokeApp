package com.example.pokeapp.presentation.ui.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

open class BaseViewModel : ViewModel() {
    var dbReference: DatabaseReference
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        dbReference = database.reference.child("User")
    }
}