package com.example.letroso.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letroso.data.local.UserEntity
import com.example.letroso.data.repository.UserRepository
import com.example.letroso.ui.state.AdminUiState
import com.example.letroso.ui.state.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AdminVM(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AdminUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            userRepository.getAllUsers().collect { list ->
                _uiState.update { it.copy(users = list) }
            }
        }
    }

    fun editUser(user: UserEntity, newEmail: String) {
        viewModelScope.launch {
            userRepository.updateUser(user.copy(email = newEmail))
        }
    }
    fun deleteUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.deleteUser(user)
            loadUsers()
        }
    }
}


