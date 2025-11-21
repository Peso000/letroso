package com.example.letroso.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.letroso.ui.factorys.AdminVMFactory
import com.example.letroso.viewmodel.AdminVM

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun TelaAdmin() {

    val contex = LocalContext.current
    val db = AppDatabase.getDatabase(context = contex)
    val userRepository = remember { UserRepository(db.userDao()) }
    val viewModel: AdminVM = viewModel(
        factory = AdminVMFactory(userRepository)
    )

    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            Header()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(15, 23, 42))
                .padding(16.dp)
        ) {

            Text(
                text = "Usuários cadastrados:",
                color = Color.White,
                fontSize = 20.sp,
            )

            Spacer(Modifier.height(20.dp))

            for (user in state.users) {
                AdminUserItem(
                    email = user.email,
                    onDelete = { viewModel.deleteUser(user) },
                    onEdit = { newEmail ->
                        viewModel.editUser(user, newEmail)
                    }
                )
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun AdminUserItem(
    email: String,
    onDelete: () -> Unit,
    onEdit: (String) -> Unit
) {
    var showEditDialog by remember { mutableStateOf(false) }
    var newEmail by remember { mutableStateOf(email) }

    if (showEditDialog) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = { Text("Editar usuário") },
            text = {
                TextField(
                    value = newEmail,
                    onValueChange = { newEmail = it },
                    singleLine = true,
                    label = { Text("Novo email") }
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    onEdit(newEmail)
                    showEditDialog = false
                }) {
                    Text("Salvar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(30, 40, 60))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = email,
            color = Color.White
        )

        Row {
            Button(
                onClick = { showEditDialog = true },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text("Editar")
            }

            Button(
                onClick = onDelete
            ) {
                Text("Excluir")
            }
        }
    }
}
