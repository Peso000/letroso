package com.example.letroso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.letroso.ui.screens.TelaCadastroUsuario
import com.example.letroso.ui.screens.TelaLogin
import com.example.letroso.ui.screens.telaJogoDiario
import com.example.letroso.ui.theme.LetrosoTheme
import com.example.letroso.viewmodel.JogoDiarioVM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navigation()
        }
    }
}

@Composable
fun navigation (){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            telaJogoDiario()
        }

        composable("login") {
            TelaLogin(
//                irParaCadastro = { navController.navigate("cadastro") },
//                irParaJogo = { navController.navigate("home") }
            )
        }

//        composable("cadastro") {
//            TelaCadastroUsuario(
//                voltar = { navController.popBackStack() }
//            )
//        }
//
//        composable("cadastroPalavras") {
//            TelaCadastroPalavra(
//                voltar = { navController.popBackStack() }
//            )
//        }
//
//        composable("admin") {
//            TelaAdmin()
//        }
//
//        composable("ranking") {
//            TelaRanking()
//        }
    }

}



