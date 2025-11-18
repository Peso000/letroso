package com.example.letroso.ui.state

import com.example.letroso.data.local.Palavra

data class WordUiState(
    val words: List<Palavra> = emptyList(),
    val randomWord: Palavra? = null
)
