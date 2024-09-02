package br.edu.isfp.dmo5.exavaliativo2dmos5.data.model

import java.time.LocalDateTime

class Journal(
    var title: String,
    var description: String,
    var localDateTime: LocalDateTime

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