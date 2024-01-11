package com.example.tahfizhhub.ui.murajaah.add

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tahfizhhub.AddMurajaahEvent
import com.example.tahfizhhub.navigasi.DestinasiNavigasi
import com.example.tahfizhhub.ui.PenyediaViewModel

object DestinasiAddMurajaah : DestinasiNavigasi {
    override val route = "add_murajaah"
    override val titleRes = "Tambah Data Murajaah"
}

@Composable
fun AddMurajaahScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    addMurajaahViewModel: AddMurajaahViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    @Composable
    fun FormInput(
        addMurajaahEvent: AddMurajaahEvent,
        modifier: Modifier = Modifier,
        onValueChange: (AddMurajaahEvent) -> Unit = {},
        enabled: Boolean = true
    ) {
        OutlinedTextField(
            value = addMurajaahEvent.juz,
            onValueChange = { onValueChange(addMurajaahEvent.copy(juz = it))},
            label = { Text(text = "Juz") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = addMurajaahEvent.surat,
            onValueChange = { onValueChange(addMurajaahEvent.copy(surat = it))},
            label = { Text(text = "Surat") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = addMurajaahEvent.ayat,
            onValueChange = { onValueChange(addMurajaahEvent.copy(ayat = it))},
            label = { Text(text = "Ayat") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = addMurajaahEvent.halaman,
            onValueChange = { onValueChange(addMurajaahEvent.copy(halaman = it))},
            label = { Text(text = "Halaman") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}

