package com.example.letroso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    val jogoDiarioVM: JogoDiarioVM = androidx.lifecycle.viewmodel.compose.viewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home") {
            telaJogoDiario(jogoDiarioVM,
                voltar = { navController.popBackStack() }
            )
        }
    }
}

