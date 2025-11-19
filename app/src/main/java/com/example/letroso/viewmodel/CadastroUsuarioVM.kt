package com.example.letroso.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letroso.data.local.UserEntity
import com.example.letroso.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CadastroUsuarioVM(
    private val repository: UserRepository
) : ViewModel() {

    val users: StateFlow<List<UserEntity>> = repository.getAllUsers()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertUser(name: String, email: String, password: String) {
        val newUser = UserEntity(
            name = name,
            email = email,
            password = password,
            nivUser = 0,
            pontuation = 0
        )

        repository.insertUser(newUser)

    }
}
