package com.example.letroso.di

import AppDatabase
import android.content.Context
import com.example.letroso.data.repository.UserRepository
import kotlin.getValue

object AppModule {
    private lateinit var database: AppDatabase

    fun init(context: Context) {
        database = AppDatabase.getInstance(context)
    }

    val userDao by lazy { database.userDao() }

    val userRepository by lazy { UserRepository(userDao) }
}