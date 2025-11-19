package com.example.letroso.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letroso.data.repository.UserRepository
import com.example.letroso.ui.state.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginVM(
    private val userRepository: UserRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun validaUser( email: String, pass: String ){
        viewModelScope.launch {
            val user = userRepository.login( email, pass )

            if( user != null ){
                _uiState.update { it.copy( loginSucess = true ) }
            }
        }

    }
}