package com.example.tahfizhhub.repository

import android.content.ContentValues
import android.util.Log
import com.example.tahfizhhub.model.MurajaahData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

interface MurajaahRepository {
    fun getNewMurajaah(): Flow<List<MurajaahData>>
    fun getAllMurajaah(): Flow<List<MurajaahData>>
    suspend fun addMurajaah(murajaahData: MurajaahData): String
    suspend fun editMurajaah(murajaahData: MurajaahData)
    suspend fun deleteMurajaah(murajaahID: String)
    fun getMurajaahById(murajaahID: String): Flow<MurajaahData>
}

class MurajaahRepositoryImpl(private val firestore : FirebaseFirestore) : MurajaahRepository {
    val user = FirebaseAuth.getInstance().currentUser
    val uid = user?.uid.toString()
    val timestampString =
        SimpleDateFormat("dd-MM-yy_HH:mm:ss", Locale.getDefault()).format(Date())

    override fun getNewMurajaah(): Flow<List<MurajaahData>> = flow {
        val snapshot = firestore.collection("User")
            .document("user001")
            .collection("Murajaah")
            .orderBy("nama", Query.Direction.ASCENDING)
            .limit(1)
            .get()
            .await()
        val murajaah = snapshot.toObjects(MurajaahData::class.java)
        emit(murajaah)
    }

    override fun getAllMurajaah(): Flow<List<MurajaahData>> = flow {
        val snapshot = firestore.collection("User")
            .document("user001")
            .collection("Murajaah")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .get()
            .await()

        val murajaah = snapshot.toObjects(MurajaahData::class.java)
        emit(murajaah)
    }.flowOn(Dispatchers.IO)

    override suspend fun addMurajaah(murajaahData: MurajaahData): String {
        return try {
            val documentReference = firestore.collection("User")
                .document("user001")
                .collection("Murajaah")
                .document(timestampString)

            murajaahData.timestamp = timestampString

            // Set the setoranID field with the auto-generated ID
            murajaahData.murajaahID = documentReference.id

            // Use the document reference to set the data in the Firestore collection
            documentReference.set(murajaahData)

            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }

    override suspend fun editMurajaah(murajaahData: MurajaahData) {
        firestore.collection("User")
            .document("user001")
            .collection("Murajaah")
            .document(murajaahData.murajaahID)
            .set(murajaahData)
            .await()
    }

    override suspend fun deleteMurajaah(murajaahID: String) {
        firestore.collection("User")
            .document("user001")
            .collection("Murajaah")
            .document(murajaahID)
            .delete()
            .await()
    }

    override fun getMurajaahById(murajaahID: String): Flow<MurajaahData> {
        return flow {
            val snapshot = firestore.collection("User")
                .document("user001")
                .collection("Setoran")
                .document(murajaahID)
                .get()
                .await()
            val murajaahData = snapshot.toObject(MurajaahData::class.java)
            emit(murajaahData!!)
        }.flowOn(Dispatchers.IO)
    }
}