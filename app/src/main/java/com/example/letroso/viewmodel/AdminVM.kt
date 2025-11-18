package com.example.letroso.viewmodel

import androidx.lifecycle.ViewModel
import com.example.letroso.ui.state.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginVM: ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun validaUser(){

    }

    fun mostraErro( erro:String ){

    }
}