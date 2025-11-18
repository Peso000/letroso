package com.example.letroso.data.repository

import com.example.letroso.data.local.UserDao
import com.example.letroso.data.logic.models.User

class UserRepository(private val UserDao: UserDao ) {
    suspend fun login(email: String, pass: String): User?{
        val user = UserDao.getUser(email, pass)

        return if(user == null) {
            null
        } else {
            user
        }
    }
}