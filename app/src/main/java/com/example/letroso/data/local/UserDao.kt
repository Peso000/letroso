package com.example.letroso.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.letroso.data.logic.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("Select * From Usuarios where email = :email and password = :pass")
    fun getUser(email: String, pass: String): UserEntity?

    @Query("Select * From Usuarios")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)
}