package br.edu.isfp.dmo5.exavaliativo2dmos5.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.dao.JournalDao
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.model.Journal

@Database(entities = [Journal::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    companion object{
        const val DATABASE_NAME = "app_journal.db"
        private lateinit var instance: AppDatabase

        fun getInstance(context: Context): AppDatabase{
            if (!::instance.isInitialized){
                synchronized(AppDatabase::class){
                    instance = Room
                        .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                        .build()
                }
            }
            return instance
        }
    }

    abstract fun getJournalDao():JournalDao

}