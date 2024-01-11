package com.example.tahfizhhub

import android.app.Application
import com.example.tahfizhhub.repository.AppContainer
import com.example.tahfizhhub.repository.SetoranContainer

class TahfizhApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = SetoranContainer()
        //
    }
}