package com.example.letroso.data.repository

import com.example.letroso.data.local.WordDao
import com.example.letroso.data.local.WordEntity
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    suspend fun insertWord(word: WordEntity) {
        wordDao.insert(word)
    }

    fun getAllWords(): Flow<List<WordEntity>> {
        return wordDao.getAllWords()
    }
}
