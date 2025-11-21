package com.example.letroso.ui.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letroso.data.repository.UserRepository
import com.example.letroso.viewmodel.AdminVM
import com.example.letroso.viewmodel.RankingVM
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class AdminVMFactory(
    private val userRepository: UserRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdminVM::class.java)) {
            return AdminVM(userRepository) as T
        }
        throw IllegalArgumentException("Não foi possível criar a ViewModel")
    }
}