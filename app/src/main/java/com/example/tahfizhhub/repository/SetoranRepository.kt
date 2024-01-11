package com.example.tahfizhhub.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import com.example.tahfizhhub.model.SetoranData
import com.example.tahfizhhub.ui.login.LoginResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


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
    val timestampString =
        SimpleDateFormat("dd-MM-yy_HH:mm:ss", Locale.getDefault()).format(Date())

    override fun getNew(): Flow<List<SetoranData>> = flow {
        val snapshot = firestore.collection("User")
            .document("user001")
            .collection("Setoran")
            .orderBy("nama", Query.Direction.ASCENDING)
            .limit(1)
            .get()
            .await()
        val kontak = snapshot.toObjects(SetoranData::class.java)
        emit(kontak)
    }

    override fun getAll(): Flow<List<SetoranData>> = flow {
        val snapshot = firestore.collection("User")
            .document("user001")
            .collection("Setoran")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .get()
            .await()

        val setoran = snapshot.toObjects(SetoranData::class.java)
        emit(setoran)
    }.flowOn(Dispatchers.IO)

    override suspend fun addSetoran(setoranData: SetoranData): String {
        return try {
            val documentReference = firestore.collection("User")
                .document("user001")
                .collection("Setoran")
                .document(timestampString)

            setoranData.timestamp = timestampString

            // Set the setoranID field with the auto-generated ID
            setoranData.setoranID = documentReference.id

            // Use the document reference to set the data in the Firestore collection
            documentReference.set(setoranData)

            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }

    override suspend fun editSetoran(setoranData: SetoranData) {
        firestore.collection("User")
            .document("user001")
            .collection("Setoran")
            .document(setoranData.setoranID)
            .set(setoranData)
            .await()
    }

    override suspend fun deleteSetoran(setoranID: String) {
        firestore.collection("User")
            .document("user001")
            .collection("Setoran")
            .document(setoranID)
            .delete()
            .await()
    }

    override fun getSetoranById(setoranID: String): Flow<SetoranData> {
        return flow {
            val snapshot = firestore.collection("User")
                .document("user001")
                .collection("Setoran")
                .document(setoranID)
                .get()
                .await()
            val setoranData = snapshot.toObject(SetoranData::class.java)
            emit(setoranData!!)
        }.flowOn(Dispatchers.IO)
    }
}

interface LoginRepository {
    suspend fun getCurrentUser(): User?
    suspend fun loginUser(email: String, password: String): LoginResult
    suspend fun logoutUser()
}

class FirebaseAuthRepository(private val firebaseAuth: FirebaseAuth) : LoginRepository {
    )
    override suspend fun getCurrentUser(): User? {
        val currentUser: FirebaseUser? = firebaseAuth.currentUser
        return currentUser?.let { User(it.uid, it.email ?: "") }
    }


    override suspend fun loginUser(email: String, password: String): LoginResult {
        return try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user

            firebaseUser?.let {
                LoginResult.Success(User(it.uid, it.email ?: ""))
            } ?: run {
                LoginResult.Error("User is null")
            }
        } catch (e: Exception) {
            LoginResult.Error(e.message ?: "Login failed")
        }
    }

    override suspend fun logoutUser() {
        firebaseAuth.signOut()
    }
}

interface SignUpRepository {
    suspend fun signUp(username: String, email: String, password: String): Result<Unit>
    suspend fun signUpWithGoogleIdToken(idToken: String): Result<Unit>
}

class FirebaseAuthSignUpRepository(private val firebaseAuth: FirebaseAuth) : SignUpRepository {
    override suspend fun signUp(username: String, email: String, password: String): Result<Unit> {
        return suspendCoroutine { continuation ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(Result.success(Unit))
                    } else {
                        continuation.resume(Result.failure(task.exception ?: Exception("Unknown error")))
                    }
                }
        }
    }

    override suspend fun signUpWithGoogleIdToken(idToken: String): Result<Unit> = suspendCoroutine { continuation ->
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    continuation.resume(Result.success(Unit))
                } else {
                    continuation.resume(Result.failure(task.exception ?: Exception("Unknown error")))
                }
            }
    }
}