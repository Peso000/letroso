package com.example.letroso.ui.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letroso.data.repository.UserRepository
import com.example.letroso.viewmodel.LoginVM
import java.lang.IllegalArgumentException

class LoginVMFactory(
    private val userRepository: UserRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginVM::class.java)){
            return LoginVM( userRepository = userRepository ) as T
        }
        throw IllegalArgumentException("Não retornou a viewmodel corretamente")
    }
}