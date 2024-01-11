package com.example.tahfizhhub.repository

import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer {
    val setoranRepository : SetoranRepository
}

class SetoranContainer : AppContainer {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override val setoranRepository: SetoranRepository by lazy {
        SetoranRepositoryImpl(firestore)
    }
}