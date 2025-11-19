package com.example.letroso.data.repository

import com.example.letroso.data.local.UserDao
import com.example.letroso.data.local.UserEntity
import com.example.letroso.data.logic.models.User

class UserRepository( private val userDao: UserDao ) {
    suspend fun login(email: String, pass: String): UserEntity?{
        val user = userDao.getUser(email, pass)

        return if(user == null) {
            null
        } else {
            user
        }
    }
}