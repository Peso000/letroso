package com.example.letroso.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Palavras")
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val word: String,
    val category: String,
    val wordLength: Int
)
