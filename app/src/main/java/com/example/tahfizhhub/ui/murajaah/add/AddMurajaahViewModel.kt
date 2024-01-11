package com.example.tahfizhhub.ui.murajaah.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tahfizhhub.AddMurajaahEvent
import com.example.tahfizhhub.AddMurajaahUIState
import com.example.tahfizhhub.repository.MurajaahRepository
import com.example.tahfizhhub.toMurajaahData

class AddMurajaahViewModel(private val murajaahRepository: MurajaahRepository) : ViewModel() {
    var addMurajaahUIState by mutableStateOf(AddMurajaahUIState())
        private set

    fun updateAddMurajaahUIState(addMurajaahEvent: AddMurajaahEvent) {
        addMurajaahUIState = AddMurajaahUIState(addMurajaahEvent = addMurajaahEvent)
    }

    suspend fun addMurajaah() {
        murajaahRepository.addMurajaah(addMurajaahUIState.addMurajaahEvent.toMurajaahData())
    }
}