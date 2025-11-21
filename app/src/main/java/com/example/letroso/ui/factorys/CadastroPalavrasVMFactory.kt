package com.example.letroso.ui.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letroso.data.repository.UserRepository
import com.example.letroso.data.repository.WordRepository
import com.example.letroso.viewmodel.CadastroUsuarioVM
import com.example.letroso.viewmodel.WordVM
import java.lang.IllegalArgumentException


@Suppress("UNCHECKED_CAST")
class CadastroPalavrasVMFactory(
    private val wordRepository: WordRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WordVM::class.java)){
            return WordVM(wordRepository) as T
        }
        throw IllegalArgumentException("Não foi possível criar a ViewModel: ${modelClass.name}")
    }
}