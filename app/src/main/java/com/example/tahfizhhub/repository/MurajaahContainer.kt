package com.example.tahfizhhub.repository

import com.google.firebase.firestore.FirebaseFirestore

interface AppMurajaahContainer {
    val murajaahRepository : MurajaahRepository
}

class MurajaahContainer : AppMurajaahContainer {
    private val firestoreMurajaah: FirebaseFirestore = FirebaseFirestore.getInstance()
    override val murajaahRepository: MurajaahRepository by lazy {
        MurajaahRepositoryImpl(firestoreMurajaah)
    }

}