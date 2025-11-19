package com.example.letroso.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuarios")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val password: String,
    val email: String,
    val nivUser: Int,
    val pontuation: Int
)
