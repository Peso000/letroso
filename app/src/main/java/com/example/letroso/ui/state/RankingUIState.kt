package com.example.letroso.ui.state

import com.example.letroso.data.local.UserEntity

data class RankingUIState(
    val loading: Boolean = true,
    val jogadores: List<UserEntity> = emptyList()
)
