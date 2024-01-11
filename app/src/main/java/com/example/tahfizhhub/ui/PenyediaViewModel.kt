package com.example.tahfizhhub.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tahfizhhub.TahfizhApplication
import com.example.tahfizhhub.ui.add.AddViewModel
import com.example.tahfizhhub.ui.detail.DetailViewModel
import com.example.tahfizhhub.ui.home.HomeViewModel

fun CreationExtras.aplikasiSetoran(): TahfizhApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TahfizhApplication)

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            AddViewModel(aplikasiSetoran().container.setoranRepository)
        }
        initializer {
            HomeViewModel(aplikasiSetoran().container.setoranRepository)
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                aplikasiSetoran().container.setoranRepository
            )
        }
    }
}
