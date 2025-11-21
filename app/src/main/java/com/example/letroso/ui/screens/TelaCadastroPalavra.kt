package com.example.letroso.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.letroso.data.repository.UserRepository
import com.example.letroso.data.repository.WordRepository
import com.example.letroso.ui.components.Header
import com.example.letroso.ui.factorys.CadastroPalavrasVMFactory
import com.example.letroso.viewmodel.WordVM

@Preview
@Composable
fun TelaCadastroPalavras(

) {
    val contex = LocalContext.current
    val db = AppDatabase.getDatabase(context = contex)
    val wordRepository = remember { WordRepository(db.wordDao()) }
    val wordViewModel : WordVM = viewModel(
        factory = CadastroPalavrasVMFactory(wordRepository = wordRepository)
    )

    var word by remember { mutableStateOf("") }
    var meaning by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Header()
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(15, 23, 42))
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Cadastro de Palavra",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = word,
                    onValueChange = { word = it },
                    label = { Text("Palavra", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = meaning,
                    onValueChange = { meaning = it },
                    label = { Text("Significado", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (word.isNotBlank() && meaning.isNotBlank()) {
                            wordViewModel.insertWord(word, meaning)
                            word = ""
                            meaning = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "Salvar", color = Color.White)
                }
            }
        }
    }

}
