package com.example.tahfizhhub.ui.setoran.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tahfizhhub.DetailUIState
import com.example.tahfizhhub.repository.SetoranRepository
import com.example.tahfizhhub.toDetailSetoranData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: SetoranRepository
) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val setoranId: String = checkNotNull(savedStateHandle[DetailDestination.setoranId])

    val uiState: StateFlow<DetailUIState> =
        repository.getSetoranById(setoranId)
            .filterNotNull()
            .map {
                DetailUIState(addEvent = it.toDetailSetoranData())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState()
            )
    suspend fun deleteSetoran() {
        repository.deleteSetoran(setoranId)
    }
}