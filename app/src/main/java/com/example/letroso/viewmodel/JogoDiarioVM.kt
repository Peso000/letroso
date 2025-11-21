package com.example.letroso.viewmodel

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letroso.data.logic.models.LetraEstado
import com.example.letroso.data.logic.models.Palavra
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

    fun validaPalavra(input: String) {
        val randomWord = uiState.value.randomWord?.word ?: return
        val result = mutableListOf<LetraEstado>()

        for (i in input.indices) {
            val c = input[i]

            val color = when {
                randomWord[i] == c -> Color(0xFF4CAF50)     // verde
                c in randomWord    -> Color(0xFFFFC107)     // amarelo
                else               -> Color(0xFF212121)     // cinza
            }

            result.add(LetraEstado(char = c, color = color))
        }

        _uiState.update { it.copy(resultado = result) }
    }
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

