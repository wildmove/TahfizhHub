package com.example.tahfizhhub.ui.murajaah.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tahfizhhub.AddMurajaahEvent
import com.example.tahfizhhub.AddMurajaahUIState
import com.example.tahfizhhub.navigasi.DestinasiNavigasi
import com.example.tahfizhhub.ui.PenyediaViewModel
import com.example.tahfizhhub.ui.TahfizhTopAppBar
import com.example.tahfizhhub.ui.setoran.add.DestinasiEntry
import kotlinx.coroutines.launch

object DestinasiAddMurajaah : DestinasiNavigasi {
    override val route = "add_murajaah"
    override val titleRes = "Tambah Data Murajaah"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMurajaahScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    addMurajaahViewModel: AddMurajaahViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TahfizhTopAppBar(
                title = DestinasiEntry.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        AddMurajaahBody(
            addMurajaahUIState = addMurajaahViewModel.addMurajaahUIState,
            onMurajaahValueChange = addMurajaahViewModel::updateAddMurajaahUIState,
            onSaveClick = {
                coroutineScope.launch {
                    addMurajaahViewModel.addMurajaah()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )

    }
}

@Composable
fun AddMurajaahBody(
    addMurajaahUIState: AddMurajaahUIState,
    onMurajaahValueChange: (AddMurajaahEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            addMurajaahEvent = addMurajaahUIState.addMurajaahEvent,
            onValueChange = onMurajaahValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

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

