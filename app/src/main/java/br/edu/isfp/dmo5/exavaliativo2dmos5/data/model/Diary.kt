package br.edu.isfp.dmo5.exavaliativo2dmos5.data.model

import java.time.LocalDateTime

class Diary(
    var title: String,
    var localDateTime: LocalDateTime,
    var description: String
)
{
    private companion object{
        var lastId: Long = 1L
    }

    var id: Long = 0L

    init {
        lastId += 1
        id = lastId
    }
}