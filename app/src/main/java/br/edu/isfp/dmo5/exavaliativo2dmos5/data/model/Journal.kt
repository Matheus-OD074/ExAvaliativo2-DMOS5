package br.edu.isfp.dmo5.exavaliativo2dmos5.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "tb_journals")
class Journal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @NonNull
    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    localDateTime: LocalDateTime = LocalDateTime.now()
)
{

    @ColumnInfo(name = "deadline")
    var dateTime: String = ""
    @Ignore
    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")
    init {
        dateTime = formatter.format(localDateTime)
    }

    fun isHighPriority(): Boolean{
        val dead = LocalDate.parse(dateTime, formatter)
        return dead <= LocalDate.now()
    }
}


