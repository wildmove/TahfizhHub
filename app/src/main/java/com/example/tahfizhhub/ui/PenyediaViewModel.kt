package com.example.tahfizhhub.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tahfizhhub.TahfizhApplication
import com.example.tahfizhhub.ui.murajaah.add.AddMurajaahViewModel
import com.example.tahfizhhub.ui.murajaah.detail.DetailMurajaahViewModel
import com.example.tahfizhhub.ui.murajaah.edit.EditMurajaahViewModel
import com.example.tahfizhhub.ui.murajaah.get.GetMurajaahViewModel
import com.example.tahfizhhub.ui.setoran.add.AddViewModel
import com.example.tahfizhhub.ui.setoran.detail.DetailViewModel
import com.example.tahfizhhub.ui.setoran.edit.EditViewModel
import com.example.tahfizhhub.ui.setoran.home.HomeViewModel

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
        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiSetoran().container.setoranRepository
            )
        }
        initializer {
            AddMurajaahViewModel(
                aplikasiSetoran().murajaahContainer.murajaahRepository
            )
        }
        initializer {
            GetMurajaahViewModel(
                aplikasiSetoran().murajaahContainer.murajaahRepository
            )
        }
        initializer {
            DetailMurajaahViewModel(
                createSavedStateHandle(),
                aplikasiSetoran().murajaahContainer.murajaahRepository
            )
        }
        initializer {
            EditMurajaahViewModel(
                createSavedStateHandle(),
                aplikasiSetoran().murajaahContainer.murajaahRepository
            )
        }
    }
}
