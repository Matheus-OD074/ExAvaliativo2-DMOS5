package br.edu.isfp.dmo5.exavaliativo2dmos5.dto

import java.time.LocalDateTime

data class JournalDto(
    var title: String,
    var description: String,
    var localDateTime: LocalDateTime,
)
