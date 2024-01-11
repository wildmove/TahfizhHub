package com.example.tahfizhhub.ui.murajaah.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tahfizhhub.DetailMurajaahUIState
import com.example.tahfizhhub.repository.MurajaahRepository
import com.example.tahfizhhub.toDetailMurajaahData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailMurajaahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: MurajaahRepository
) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val murajaahId: String = checkNotNull(savedStateHandle[DetailMurajaahDestination.murajaahId])
    val uiState: StateFlow<DetailMurajaahUIState> =
        repository.getMurajaahById(murajaahId)
            .filterNotNull()
            .map {
                DetailMurajaahUIState(addMurajaahEvent = it.toDetailMurajaahData())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailMurajaahUIState()
            )
    suspend fun deleteMurajaah() {
        repository.deleteMurajaah(murajaahId)
    }
}