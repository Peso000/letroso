package com.example.letroso.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letroso.data.local.Palavra
import com.example.letroso.data.networking.RetrofitInstance
import com.example.letroso.ui.state.WordUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Collections.list

class JogoDiarioVM : ViewModel() {

    private val _uiState = MutableStateFlow(WordUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchWord() {
        viewModelScope.launch {
            _uiState.update { it.copy( loading = true ) }

            try {
                val response = RetrofitInstance.api.getWords()

                if (response.isSuccessful) {
                    val list = response.body() ?: emptyList()
                    _uiState.update { it.copy(
                        words = list,
                        randomWord = list.random(),
                        loading = false
                        )
                    }
                } else {
                    println("Erro: ${response.code()}")
                }
            } catch (e: Exception) {
                println("Falha na requisição: ${e.message}")
            }
        }
    }
}
