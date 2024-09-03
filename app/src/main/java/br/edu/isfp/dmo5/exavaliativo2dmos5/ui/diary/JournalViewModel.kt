package br.edu.isfp.dmo5.exavaliativo2dmos5.ui.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.model.Journal
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.repository.JournalRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

class JournalViewModel(private val repository: JournalRepository): ViewModel() {

    private var journalId: Long = -1

    private val _saved = MutableLiveData<Boolean>()
    val saved: LiveData<Boolean> = _saved

    private val _isUpdate = MutableLiveData<Boolean>()
    val isUpdate: LiveData<Boolean> = _isUpdate

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _dateTimeString = MutableLiveData<String>()
    val dateTimeString: LiveData<String> = _dateTimeString

    init {
        _isUpdate.value = false
    }

    fun saveJournal(title: String, description: String, dateTime: LocalDateTime){
        val journal = Journal(title = title, description = description, localDateTime = dateTime)
        if (_isUpdate.value == false) {
            viewModelScope.launch {
                _saved.value = repository.insert(journal)
            }
        } else {
            journal.id = journalId
            viewModelScope.launch {
                _saved.value = repository.update(journal)
                journalId = -1
            }
        }
    }

    fun showEvent(id: Long) {
        viewModelScope.launch {
            val journal = repository.findById(id)
            if (journal != null){
                journalId = journal.id
                _isUpdate.value = true
                _description.value = journal.description
                _title.value = journal.title
                _dateTimeString.value = journal.dateTime
            }
        }
    }

}