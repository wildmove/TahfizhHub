package com.example.tahfizhhub.ui.setoran.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tahfizhhub.AddEvent
import com.example.tahfizhhub.AddUIState
import com.example.tahfizhhub.repository.SetoranRepository
import com.example.tahfizhhub.toSetoranData

class AddViewModel(private val setoranRepository: SetoranRepository) : ViewModel() {
    var addUIState by mutableStateOf(AddUIState())
        private set

    fun updateAddUIState(addEvent: AddEvent) {
        addUIState = AddUIState(addEvent = addEvent)
    }

    suspend fun addSetoran() {
        setoranRepository.addSetoran(addUIState.addEvent.toSetoranData())
    }
}
