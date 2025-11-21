package com.example.letroso.ui.state

import androidx.compose.ui.graphics.Color
import com.example.letroso.data.logic.models.LetraEstado
import com.example.letroso.data.logic.models.Letters
import com.example.letroso.data.logic.models.Palavra


data class WordUiState(
    val loading: Boolean = false,
    val words: List<Palavra> = emptyList(),
    val randomWord: Palavra? = null,
    val indexCorrectLetters: MutableList<Int> = mutableListOf(),
    val indexPartiallyLetters : MutableList<Int> = mutableListOf(),
    val resultado: MutableList<LetraEstado> = mutableListOf()
)
