package com.example.letroso.ui.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letroso.data.repository.UserRepository
import com.example.letroso.viewmodel.CadastroUsuarioVM
import java.lang.IllegalArgumentException

class CadastroUsuarioVMFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CadastroUsuarioVM::class.java)){
            return CadastroUsuarioVM(userRepository) as T
        }
        throw IllegalArgumentException("Não foi possível criar a ViewModel: ${modelClass.name}")
    }
}
