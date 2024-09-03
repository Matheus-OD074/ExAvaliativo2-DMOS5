package br.edu.isfp.dmo5.exavaliativo2dmos5.ui.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.repository.JournalRepository

class JournalViewModelFactory(private val repository: JournalRepository):
ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JournalViewModel::class.java)){
            return JournalViewModel(repository) as T
        }
        throw IllegalArgumentException("View model desconhecido")
    }
}