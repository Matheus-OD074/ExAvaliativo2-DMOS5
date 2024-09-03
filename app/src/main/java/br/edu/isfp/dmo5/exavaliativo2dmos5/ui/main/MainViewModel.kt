package br.edu.isfp.dmo5.exavaliativo2dmos5.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.model.Journal
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.repository.JournalRepository
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MainViewModel(private val repository: JournalRepository): ViewModel() {

    private val _journals = MutableLiveData<List<Journal>>()
    val journals: LiveData<List<Journal>> = _journals

    init {
        loadData()
    }

    fun loadData(){
        viewModelScope.launch {
            val list = repository.findAll()
            _journals.postValue(list)
        }
    }

    fun deleteJournal(id:Long){
        viewModelScope.launch {
            val journal = repository.findById(id)
            if (journal != null){
                repository.remove(journal)
                loadData()
            }
        }
    }

}