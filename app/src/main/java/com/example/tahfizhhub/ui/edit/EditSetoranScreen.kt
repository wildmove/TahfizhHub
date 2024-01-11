package com.example.tahfizhhub.ui.edit

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
import com.example.tahfizhhub.ui.add.AddBody
import kotlinx.coroutines.launch

object EditSetoranDestination : DestinasiNavigasi {
    override val route = "item_edit_setoran"
    override val titleRes = "Edit Setoran"
    const val setoranId = "itemId"
    val routeWithArgs = "${EditSetoranDestination.route}/{$setoranId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSetoranScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
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
        AddBody(
            addUIState = viewModel.setoranUiState,
            onSetoranValueChange = viewModel::updateUIState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.editSetoran()
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