package com.example.letroso.ui.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letroso.data.repository.UserRepository
import com.example.letroso.viewmodel.RankingVM
import java.lang.IllegalArgumentException

class RankingVMFactory(
    private val userRepository: UserRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RankingVM::class.java)) {
            return RankingVM(userRepository) as T
        }
        throw IllegalArgumentException("Não foi possível criar a ViewModel")
    }
}
