package com.example.tahfizhhub.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tahfizhhub.AddEvent
import com.example.tahfizhhub.AddUIState
import com.example.tahfizhhub.repository.SetoranRepository
import com.example.tahfizhhub.toSetoranData
import com.example.tahfizhhub.toUIStateSetoranData
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: SetoranRepository
) : ViewModel() {
    var setoranUiState by mutableStateOf(AddUIState())
        private set

    private val setoranId: String = checkNotNull(savedStateHandle[EditSetoranDestination.setoranId])

    init {
        viewModelScope.launch {
            setoranUiState =
                repository.getSetoranById(setoranId)
                    .filterNotNull()
                    .first()
                    .toUIStateSetoranData()
        }
    }

    fun updateUIState(addEvent: AddEvent) {
        setoranUiState = setoranUiState.copy(addEvent = addEvent)
    }

    suspend fun editSetoran() {
        repository.editSetoran(setoranUiState.addEvent.toSetoranData())
    }
}