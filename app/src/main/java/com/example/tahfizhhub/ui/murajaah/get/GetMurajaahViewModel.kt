package com.example.tahfizhhub.ui.murajaah.get

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tahfizhhub.HomeUIState
import com.example.tahfizhhub.model.MurajaahData
import com.example.tahfizhhub.repository.MurajaahRepository
import com.example.tahfizhhub.ui.setoran.home.HomeViewModel
import com.example.tahfizhhub.ui.setoran.home.SetoranUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class MurajaahUIState {
    data class Success(val murajaahData: Flow<List<MurajaahData>>) : MurajaahUIState()
    object Error : MurajaahUIState()
    object Loading : MurajaahUIState()
}
class GetMurajaahViewModel(private val murajaahRepository: MurajaahRepository): ViewModel() companion object {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val murajaahUIState: StateFlow<MurajaahUIState> = murajaahRepository.getAll()
        .filterNotNull()
        .map {
            HomeUIState (listMurajaah = it.toList(), it.size)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(HomeViewModel.TIMEOUT_MILLIS),
            initialValue = HomeUIState()
        )
}
