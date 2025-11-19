package com.example.letroso.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letroso.data.repository.UserRepository
import com.example.letroso.ui.state.RankingUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RankingVM(
    private val repository: UserRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(RankingUIState())
    val uiState: StateFlow<RankingUIState> = _uiState.asStateFlow()

    init {
        carregarRanking()
    }

    fun carregarRanking() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }
            val users = repository.getAllUsersSortedByPoints()
            _uiState.update {
                it.copy(
                    jogadores = users,
                    loading = false
                )
            }
        }
    }
}