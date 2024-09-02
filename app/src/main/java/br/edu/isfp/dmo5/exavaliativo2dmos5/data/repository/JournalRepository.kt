package br.edu.isfp.dmo5.exavaliativo2dmos5.data.repository

import android.util.Log
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.model.Journal
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore

class JournalRepository {
    companion object{
        const val TAG = "EXAVALIATIVO2"
        const val COLLECTION = "diario"
        const val ATT_TITLE = "title"
        const val DOC_ID = "id"
        const val ATTR_DATETIME = "localDateTime"
        const val ATTR_DESCRIPTION = "description"
    }

    private val database = Firebase.firestore

    fun findAll(callback: (List<Journal>) -> Unit){
        database.collection(COLLECTION)
            .orderBy(ATT_TITLE, Query.Direction.ASCENDING)
            .addSnapshotListener{ querySnapshot, exception ->
                if (exception != null){
                    Log.e(TAG, "Listen Fail.")
                    callback(emptyList())
                    return@addSnapshotListener
                }
                if (querySnapshot != null){
                    val list = querySnapshot.toObjects(Journal::class.java)
                    callback(list)
                }else{
                    callback(emptyList())
                }
            }
    }

    fun insert(journal: Journal, callback: (Boolean) -> Unit){
        database.collection(COLLECTION)
            .add(journal)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }
    }

    fun remove(position: Int, callback: (Boolean) -> Unit){
        database.collection(COLLECTION)
            .document(DOC_ID).delete()
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }

}