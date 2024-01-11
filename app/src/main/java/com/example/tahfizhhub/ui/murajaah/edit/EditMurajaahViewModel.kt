package com.example.tahfizhhub.ui.murajaah.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tahfizhhub.AddMurajaahEvent
import com.example.tahfizhhub.AddMurajaahUIState
import com.example.tahfizhhub.repository.MurajaahRepository
import com.example.tahfizhhub.toMurajaahData
import com.example.tahfizhhub.toUIStateMurajaahData
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditMurajaahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: MurajaahRepository
) : ViewModel() {
    var murajaahUiState by mutableStateOf(AddMurajaahUIState())
        private set

    private val murajaahId: String = checkNotNull(savedStateHandle[EditMurajaahDestination.murajaahId])

    init {
        viewModelScope.launch {
            murajaahUiState =
                repository.getMurajaahById(murajaahId)
                    .filterNotNull()
                    .first()
                    .toUIStateMurajaahData()
        }
    }

    fun updateMurajaahUIState(addMurajaahEvent: AddMurajaahEvent) {
        murajaahUiState = murajaahUiState.copy(addMurajaahEvent = addMurajaahEvent)
    }

    suspend fun editMurajaah() {
        repository.editMurajaah(murajaahUiState.addMurajaahEvent.toMurajaahData())
    }
}