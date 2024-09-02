package br.edu.isfp.dmo5.exavaliativo2dmos5.data.repository

import android.util.Log
import androidx.appcompat.view.ActionMode.Callback
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.model.Diary
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore

class DiaryRepository {
    companion object{
        const val TAG = "EXAVALIATIVO2"
        const val COLLECTION = "diario"
        const val ATT_TITLE = "titulo"
        const val ATTR_DATETIME = "data-horario"
        const val ATTR_DESCRIPTION = "descricao"
    }

    private val database = Firebase.firestore

    fun findAll(callback: (List<Diary>) -> Unit){
        database.collection(COLLECTION)
            .orderBy(ATT_TITLE, Query.Direction.ASCENDING)
            .addSnapshotListener{ querySnapshot, exception ->
                if (exception != null){
                    Log.e(TAG, "Listen Fail.")
                    callback(emptyList())
                    return@addSnapshotListener
                }
                if (querySnapshot != null){
                    val list = querySnapshot.toObjects(Diary::class.java)
                    callback(list)
                }else{
                    callback(emptyList())
                }
            }
    }

    fun insert(diary: Diary, callback: (Boolean) -> Unit){
        database.collection(COLLECTION)
            .add(diary)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }
    }

}