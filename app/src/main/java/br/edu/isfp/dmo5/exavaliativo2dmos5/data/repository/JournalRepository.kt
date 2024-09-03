package br.edu.isfp.dmo5.exavaliativo2dmos5.data.repository

import android.content.Context
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.database.AppDatabase
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.model.Journal


class JournalRepository(context: Context) {
    private val database = AppDatabase.getInstance(context)
    private val dao = database.getJournalDao()
    suspend fun insert(journal: Journal): Boolean{
        return dao.create(journal) > 0
    }
    suspend fun update(journal: Journal): Boolean{
        return dao.update(journal) > 0
    }
    suspend fun remove(journal: Journal): Boolean{
        return dao.delete(journal) > 0
    }
    suspend fun findById(id: Long): Journal{
        return dao.getJournal(id)
    }
    suspend fun findAll(): List<Journal>{
        return dao.getAll()
    }
}
