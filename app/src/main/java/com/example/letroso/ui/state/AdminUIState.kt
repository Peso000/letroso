package com.example.letroso.ui.state

import com.example.letroso.data.local.UserEntity

data class AdminUiState(
    val users: List<UserEntity> = emptyList()
)
