package com.example.letroso.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letroso.ui.components.Header
import com.example.letroso.viewmodel.JogoDiarioVM

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun telaJogoDiario(
    /*viewModel: JogoDiarioVM,
    voltar: () -> Unit*/
){
    Scaffold(
        topBar = {
            Header()
        }
    ){  paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Color(15,23,42))
        ){
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                var texto by remember { mutableStateOf("") }

                Spacer(Modifier.height(100.dp))
                TextField(
                    value = texto,
                    onValueChange = { texto = it },
                    label = {Text("Teste")},
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}