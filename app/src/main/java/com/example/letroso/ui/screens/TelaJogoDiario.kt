package com.example.letroso.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letroso.data.local.Palavra
import com.example.letroso.ui.components.Header
import com.example.letroso.viewmodel.JogoDiarioVM

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun telaJogoDiario(
//    viewModel: JogoDiarioVM,
    voltar: () -> Unit
){
    val viewModel: JogoDiarioVM = viewModel()
    LaunchedEffect(Unit) {
        viewModel.fetchWord()
    }


    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            Header(voltar = voltar)
        }
    ){  paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Color(15,23,42))
        ){
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(Modifier.height(100.dp))
                if(state.loading == true){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Carregando palavra...",
                            color = Color.White
                        )
                    }
                    Spacer(Modifier.height(100.dp))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    var textoInput by remember { mutableStateOf("") }
                    val words =  state.words
                    val randomWord = state.randomWord



                    TextField(
                        value = textoInput,
                        onValueChange = { textoInput = it },
                        label = {Text("Teste")}
                    )


                    Text(
                        randomWord?.word ?: "Nada",
                        color = Color.White
                    )


                    //Button() { }

                }
            }
        }
    }
}
