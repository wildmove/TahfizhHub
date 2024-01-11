package com.example.tahfizhhub.repository

import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer {
    val tahfizhRepository : TahfizhRepository
}

class TahfizhContainer : AppContainer {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override val tahfizhRepository: TahfizhRepository by lazy {
        TahfizhRepositoryImpl(firestore)
    }
}