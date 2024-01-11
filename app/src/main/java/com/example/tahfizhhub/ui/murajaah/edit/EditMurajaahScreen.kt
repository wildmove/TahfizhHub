package com.example.tahfizhhub.ui.murajaah.edit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tahfizhhub.navigasi.DestinasiNavigasi
import com.example.tahfizhhub.ui.PenyediaViewModel
import com.example.tahfizhhub.ui.TahfizhTopAppBar
import com.example.tahfizhhub.ui.murajaah.add.AddMurajaahBody
import com.example.tahfizhhub.ui.setoran.add.AddBody
import com.example.tahfizhhub.ui.setoran.edit.EditSetoranDestination
import kotlinx.coroutines.launch

object EditMurajaahDestination : DestinasiNavigasi {
    override val route = "item_edit_murajaah"
    override val titleRes = "Edit Murajaah"
    const val murajaahId = "itemId"
    val routeWithArgs = "$route/{$murajaahId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMurajaahScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditMurajaahViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TahfizhTopAppBar(
                title = EditSetoranDestination.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        AddMurajaahBody(
            addMurajaahUIState = viewModel.murajaahUiState,
            onMurajaahValueChange = viewModel::updateMurajaahUIState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.editMurajaah()
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