package com.example.tahfizhhub.ui.murajaah.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tahfizhhub.DetailMurajaahUIState
import com.example.tahfizhhub.model.MurajaahData
import com.example.tahfizhhub.navigasi.DestinasiNavigasi
import com.example.tahfizhhub.toMurajaahData
import com.example.tahfizhhub.ui.PenyediaViewModel
import com.example.tahfizhhub.ui.TahfizhTopAppBar
import com.example.tahfizhhub.ui.setoran.detail.DetailDestination
import kotlinx.coroutines.launch

object DetailMurajaahDestination : DestinasiNavigasi {
    override val route = "murajaah_details"
    override val titleRes = "Detail Murajaah"
    const val murajaahId = "itemId"
    val routeWithArgs = "$route/{$murajaahId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMurajaahScreen(
    navigateToEditMurajaah: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailMurajaahViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TahfizhTopAppBar(
                title = DetailDestination.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEditMurajaah(uiState.value.addMurajaahEvent.murajaahID) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "",
                )
            }
        }, modifier = modifier
    ) { innerPadding ->
        ItemDetailsBody(
            detailMurajaahUIState = uiState.value,
            onDelete = {
                coroutineScope.launch {
                    viewModel.deleteMurajaah()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            )

    }
}

@Composable
private fun ItemDetailsBody(
    detailMurajaahUIState: DetailMurajaahUIState,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
        ItemDetails(
            murajaahData = detailMurajaahUIState.addMurajaahEvent.toMurajaahData(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedButton(
            onClick = { deleteConfirmationRequired = true },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete")
        }
        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDelete()
                },
                onDeleteCancel = { deleteConfirmationRequired = false },
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Composable
fun ItemDetails(
    murajaahData: MurajaahData, modifier: Modifier = Modifier
) {
   Card (
       modifier = modifier, colors = CardDefaults.cardColors(
           containerColor = MaterialTheme.colorScheme.primaryContainer,
           contentColor = MaterialTheme.colorScheme.onPrimaryContainer
       )
   ) {
       Column(
           modifier = Modifier
               .fillMaxWidth()
               .padding(12.dp),
           verticalArrangement = Arrangement.spacedBy(12.dp)
       ) {
           ItemDetailsRow(
               labelResID ="Juz",
               itemDetail = murajaahData.juz,
               modifier = Modifier.padding(
                   horizontal = 12.dp
               )
           )
           ItemDetailsRow(
               labelResID = "Surat",
               itemDetail = murajaahData.surat,
               modifier = Modifier.padding(
                   horizontal = 12.dp
               )
           )
           ItemDetailsRow(
               labelResID ="Ayat",
               itemDetail = murajaahData.ayat,
               modifier = Modifier.padding(
                   horizontal = 12.dp
               )
           )
           ItemDetailsRow(
               labelResID ="Halaman",
               itemDetail = murajaahData.halaman,
               modifier = Modifier.padding(
                   horizontal = 12.dp
               )
           )
       }
   }
}

@Composable
private fun ItemDetailsRow(
    labelResID: String, itemDetail: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = labelResID, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit, onDeleteCancel: () -> Unit, modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { /* Do nothing */ },
        title = { Text("Are you sure") },
        text = { Text("Delete") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "No")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        })
}