package com.example.letroso.data.logic.models

data class User(
    val id: Int = 0,
    val name: String,
    val password: String,
    val email: String,
    val nivUser: Int,
    val pontuation: Int
)
