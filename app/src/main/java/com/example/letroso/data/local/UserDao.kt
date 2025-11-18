package com.example.letroso.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.letroso.data.logic.models.User

interface UserDao {

    @Query("Select * From usuarios where email = :email and password = :pass")
    suspend fun getUser(email: String, pass: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
}