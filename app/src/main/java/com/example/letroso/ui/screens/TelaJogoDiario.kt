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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letroso.data.logic.models.Palavra
import com.example.letroso.ui.components.Header
import com.example.letroso.viewmodel.JogoDiarioVM

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun telaJogoDiario() {

    val viewModel: JogoDiarioVM = viewModel()

    LaunchedEffect(Unit) {
        viewModel.fetchWord()
    }

    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            Header()
        }
    ){ paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(15,23,42))
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(Modifier.height(60.dp))

                if (state.loading == true) {
                    Text(
                        text = "Carregando palavra...",
                        color = Color.White
                    )
                    Spacer(Modifier.height(40.dp))
                }

                var textoInput by remember { mutableStateOf("") }

                TextField(
                    value = textoInput,
                    onValueChange = { textoInput = it },
                    label = { Text("Digite a palavra") }
                )

                Spacer(Modifier.height(20.dp))

                Button(onClick = {
                    if (textoInput.length == (state.randomWord?.word?.length ?: 0)) {
                        viewModel.validaPalavra(textoInput)
                    }
                }) {
                    Text("Enviar")
                }

                Spacer(Modifier.height(40.dp))

                if (textoInput.isNotEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        val correct = state.indexCorrectLetters
                        val partial = state.indexPartiallyLetters

                        for (i in textoInput.indices) {
                            val color = when {
                                i in correct -> Color(10, 120, 10)
                                i in partial -> Color(200, 150, 0)
                                else -> Color(60, 60, 60)
                            }

                            Letter(
                                letter = textoInput[i],
                                color = color
                            )

                            Spacer(Modifier.width(5.dp))
                        }
                    }
                }

                Spacer(Modifier.height(40.dp))

                Text(
                    text = state.randomWord?.word ?: "",
                    color = Color.White
                )
            }
        }
    }
}


@Composable
fun Letter(letter: Char, color: Color) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .background(color)
    ) {
        Text(
            text = letter.toString(),
            color = Color.White,
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
