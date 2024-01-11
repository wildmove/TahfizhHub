package com.example.tahfizhhub.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tahfizhhub.HomeUIState
import com.example.tahfizhhub.model.SetoranData
import com.example.tahfizhhub.repository.SetoranRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class SetoranUIState {
    data class Success(val setoranData: Flow<List<SetoranData>>) : SetoranUIState()
    object Error : SetoranUIState()
    object Loading : SetoranUIState()
}

class HomeViewModel(private val setoranRepository: SetoranRepository): ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUIState: StateFlow<HomeUIState> = setoranRepository.getAll()
        .filterNotNull()
        .map {
            HomeUIState (listSetoran = it.toList(), it.size)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIState()
        )
}