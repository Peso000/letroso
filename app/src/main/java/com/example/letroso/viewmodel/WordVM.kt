package com.example.letroso.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letroso.data.local.WordEntity
import com.example.letroso.data.repository.WordRepository
import com.example.letroso.ui.state.WordUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WordVM(
    private val repository: WordRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WordUiState())
    val uiState = _uiState.asStateFlow()

    fun insertWord(word: String, category: String) {
        val newWord = WordEntity(word = word, category = category, wordLength = word.length)
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertWord(newWord)
        }
    }
}
