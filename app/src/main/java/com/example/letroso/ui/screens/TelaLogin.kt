package com.example.letroso.ui.screens

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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letroso.data.repository.UserRepository
import com.example.letroso.ui.factorys.LoginVMFactory
import com.example.letroso.viewmodel.LoginVM

@Composable
fun TelaLogin(
    irParaCadastro: () -> Unit,
    irParaJogo: () -> Unit
){
    val contex = LocalContext.current
    val database = AppDatabase.getInstance(context = contex)
    val userRepository = remember { UserRepository(database.userDao()) }
    val loginVM : LoginVM = viewModel(
        factory = LoginVMFactory(userRepository = userRepository)
    )
    val state by loginVM.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(15, 23, 42))
    ){
        Column(
            modifier = Modifier.align(alignment = Alignment.Center)
        ) {
            var textoEmailInput by remember { mutableStateOf("") }
            var textoPassInput by remember { mutableStateOf("") }

            Text(
                text = "Letroso",
                fontSize = 40.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                TextField(
                    value = textoEmailInput,
                    onValueChange = { textoEmailInput = it },
                    label = { Text("Email") }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                TextField(
                    value = textoPassInput,
                    onValueChange = { textoPassInput = it },
                    label = { Text("Senha") }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Button(onClick = {
                    val user = loginVM.validaUser(textoEmailInput , textoPassInput)
                }) {
                    Text("Entrar")
                }
                Spacer( modifier = Modifier.width(15.dp))
                Button( onClick = { irParaCadastro() } ) {
                    Text("Cadastrar")
                }
            }

            LaunchedEffect(state.loginSucess) {
                if(state.loginSucess){
                    irParaJogo()
                }
            }
        }

    }
}