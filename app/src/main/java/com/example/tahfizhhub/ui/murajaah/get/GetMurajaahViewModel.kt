package com.example.tahfizhhub.ui.murajaah.get

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tahfizhhub.MurajaahUIState
import com.example.tahfizhhub.model.MurajaahData
import com.example.tahfizhhub.repository.MurajaahRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class DataMurajaahUIState {
    data class Success(val murajaahData: Flow<List<MurajaahData>>) : DataMurajaahUIState()
    object Error : DataMurajaahUIState()
    object Loading : DataMurajaahUIState()
}
class GetMurajaahViewModel(private val murajaahRepository: MurajaahRepository): ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val murajaahUIState: StateFlow<MurajaahUIState> = murajaahRepository.getAllMurajaah()
        .filterNotNull()
        .map {
            MurajaahUIState (listMurajaah = it.toList(), it.size)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = MurajaahUIState()
        )


}
