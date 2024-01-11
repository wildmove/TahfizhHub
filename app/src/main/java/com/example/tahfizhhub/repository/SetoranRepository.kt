package com.example.tahfizhhub.repository

import android.content.ContentValues
import android.util.Log
import com.example.tahfizhhub.model.SetoranData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await


interface SetoranRepository {
    fun getNew(): Flow<List<SetoranData>>
    fun getAll(): Flow<List<SetoranData>>
    suspend fun addSetoran(setoranData: SetoranData): String
    suspend fun editSetoran(setoranData: SetoranData)
    suspend fun deleteSetoran(setoranID: String)
    fun getSetoranById(setoranID: String): Flow<SetoranData>
}

class SetoranRepositoryImpl(private val firestore : FirebaseFirestore) : SetoranRepository {
    val user = FirebaseAuth.getInstance().currentUser
    val uid = user?.uid.toString()

    override fun getNew(): Flow<List<SetoranData>> = flow {
        val snapshot = firestore.collection("Kontak")
            .orderBy("nama", Query.Direction.ASCENDING)
            .limit(1)
            .get()
            .await()
        val kontak = snapshot.toObjects(SetoranData::class.java)
        emit(kontak)
    }

    override fun getAll(): Flow<List<SetoranData>> = flow {
        val snapshot = firestore.collection("Resto")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .get()
            .await()

        val setoran = snapshot.toObjects(SetoranData::class.java)
        emit(setoran)
    }.flowOn(Dispatchers.IO)

    override suspend fun addSetoran(setoranData: SetoranData): String {
        return try {
            val documentReference = firestore.collection("User")
                .document(uid)
                .collection("Setoran")
                .add(setoranData)
                .await()
            // Update the Kontak with the Firestore-generated DocumentReference
            firestore.collection("Kontak").document(documentReference.id)
                .set(setoranData.copy(setoranID = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }

    override suspend fun editSetoran(setoranData: SetoranData) {
        firestore.collection("User")
            .document(uid)
            .collection("Setoran")
            .document(setoranData.setoranID)
            .set(setoranData)
            .await()
    }

    override suspend fun deleteSetoran(setoranID: String) {
        firestore.collection("User")
            .document(uid)
            .collection("Setoran")
            .document(setoranID)
            .delete()
            .await()
    }

    override fun getSetoranById(setoranID: String): Flow<SetoranData> {
        return flow {
            val snapshot = firestore.collection("User")
                .document(uid)
                .collection("Setoran")
                .document(setoranID)
                .get()
                .await()
            val setoranData = snapshot.toObject(SetoranData::class.java)
            emit(setoranData!!)
        }.flowOn(Dispatchers.IO)
    }
}