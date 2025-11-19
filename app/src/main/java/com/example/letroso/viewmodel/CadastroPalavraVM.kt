package com.example.letroso.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letroso.data.local.WordEntity
import com.example.letroso.data.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WordViewModel(
    private val repository: WordRepository
) : ViewModel() {

    val words: StateFlow<List<WordEntity>> = repository.getAllWords()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertWord(word: String, category: String) {
        val newWord = WordEntity(word = word, category = category, wordLength = word.length)
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertWord(newWord)
        }
    }
}
