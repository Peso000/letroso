package com.example.letroso.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("Select * From Usuarios where email = :email and password = :pass")
    suspend fun getUser(email: String, pass: String): UserEntity?

    @Query("Select * From Usuarios")

    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)
}