package br.edu.isfp.dmo5.exavaliativo2dmos5.data.dao

import br.edu.isfp.dmo5.exavaliativo2dmos5.data.model.Diary

class DiaryDao {

    private val diarys = mutableListOf<Diary>()

    fun getAllDiary(): List<Diary> = diarys

    fun addDiary(diary: Diary){
        diarys.add(diary)
    }

    fun getDiarys(id: Long): Diary{
        return diarys.stream().filter { item -> item.id == id }.findFirst().orElse(null)
    }
}