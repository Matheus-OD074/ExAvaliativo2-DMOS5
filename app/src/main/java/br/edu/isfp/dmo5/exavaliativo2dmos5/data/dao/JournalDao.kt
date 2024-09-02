package br.edu.isfp.dmo5.exavaliativo2dmos5.data.dao

import br.edu.isfp.dmo5.exavaliativo2dmos5.data.model.Journal

class JournalDao {

    private val journals = mutableListOf<Journal>()

    fun getAllDiary(): List<Journal> = journals

    fun addDiary(journal: Journal){
        journals.add(journal)
    }

    fun getDiarys(id: Long): Journal{
        return journals.stream().filter { item -> item.id == id }.findFirst().orElse(null)
    }
}