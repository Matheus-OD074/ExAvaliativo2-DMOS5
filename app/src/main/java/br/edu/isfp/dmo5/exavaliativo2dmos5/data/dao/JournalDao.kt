package br.edu.isfp.dmo5.exavaliativo2dmos5.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.model.Journal

@Dao
interface JournalDao {

    @Insert
    suspend fun create(journal: Journal): Long

    @Query("SELECT * FROM tb_journals ORDER BY description")
    suspend fun getAll(): List<Journal>

    @Query("SELECT * FROM tb_journals WHERE id = :id")
    suspend fun getJournal(id: Long): Journal

    @Update
    suspend fun update(journal: Journal): Int

    @Delete
    suspend fun delete(journal: Journal): Int
}