package com.example.pokeapp.domain.model

data class UserModel(
    val id: String? = null,
    val name: String,
    val lastName: String,
    val password: String,
    val email: String
)
