package com.example.letroso.data.repository

import com.example.letroso.data.local.UserDao
import com.example.letroso.data.local.UserEntity
import com.example.letroso.data.logic.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first

class UserRepository( private val userDao: UserDao ) {
    suspend fun login(email: String, pass: String): UserEntity?{
        val user = userDao.getUser(email, pass)

        return if(user == null) {
            null
        } else {
            user
        }
    }

    fun getAllUsers(): Flow<List<UserEntity>> {
        return userDao.getAllUsers()
    }

    fun insertUser(user: UserEntity){
        userDao.insertUser(user)
    }

    suspend fun getAllUsersSortedByPoints(): List<UserEntity> {
        return getAllUsers().first().sortedByDescending { it.pontuation }
    }

    suspend fun deleteUser(user: UserEntity) {
        userDao.deleteUser(user)
    }

    suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }

}