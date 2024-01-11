package com.example.tahfizhhub.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tahfizhhub.TahfizhApplication

fun CreationExtras.aplikasiSetoran(): TahfizhApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TahfizhApplication)

object PenyediaViewModel {
    val Factory = viewModelFactory {

    }
}
