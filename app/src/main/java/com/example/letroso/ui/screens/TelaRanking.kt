package com.example.letroso.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letroso.data.repository.UserRepository
import com.example.letroso.ui.components.Header
import com.example.letroso.ui.factorys.RankingVMFactory
import com.example.letroso.viewmodel.RankingVM

@Preview
@Composable
fun TelaRanking(
//    userRepository: UserRepository,
//    voltar: () -> Unit
) {
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context = context)
    val userRepository = UserRepository(db.userDao())
    val rankingVM: RankingVM = viewModel(
        factory = RankingVMFactory(userRepository)
    )
    val state by rankingVM.uiState.collectAsState()

    Scaffold(
        topBar = {
            Header(/*voltar = voltar*/)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(15,23,42))
                .padding(paddingValues)
        ) {
            if(state.loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text("Carregando...", color = Color.White)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    itemsIndexed(state.jogadores) { index, jogador ->
                        RankingRow(posicao = index + 1, jogador = jogador)
                    }
                }
            }
        }
    }
}

@Composable
fun RankingRow(posicao: Int, jogador: com.example.letroso.data.local.UserEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(30, 30, 30))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$posicao",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.width(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = jogador.name,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "${jogador.pontuation} pts",
            color = Color.Yellow,
            fontSize = 18.sp
        )
    }
}
