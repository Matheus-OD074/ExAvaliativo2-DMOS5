package br.edu.isfp.dmo5.exavaliativo2dmos5.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.model.Journal
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.repository.JournalRepository
import java.time.LocalDateTime

class MainViewModel: ViewModel() {

    private val repository = JournalRepository()

    private val _journals = MutableLiveData<List<Journal>>()
    val journals: LiveData<List<Journal>> = _journals

    private val _inserted = MutableLiveData<Boolean>()
    val inserted: LiveData<Boolean> = _inserted

    private val _removed = MutableLiveData<Boolean>()
    val removed: LiveData<Boolean> = _removed

    init {
        loadData()
    }

    fun insert(title: String, desc: String, dateTime: LocalDateTime){
        val journal = Journal(title, desc, dateTime)
        repository.insert(journal, { result ->
            _inserted.value = result
        })
    }

    fun handleDelete(position: Int){
        repository.remove(position, { result ->
            _removed.value = result
        })

        loadData()
    }

    private fun loadData(){
        repository.findAll { list -> _journals.value = list }
    }

}