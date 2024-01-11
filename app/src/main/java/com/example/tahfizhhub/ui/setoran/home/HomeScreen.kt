package com.example.tahfizhhub.ui.setoran.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tahfizhhub.model.SetoranData
import com.example.tahfizhhub.navigasi.DestinasiNavigasi
import com.example.tahfizhhub.ui.PenyediaViewModel
import com.example.tahfizhhub.ui.TahfizhTopAppBar
import java.text.SimpleDateFormat
import java.util.Locale

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "setoran"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateBack: () -> Unit,
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},

    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TahfizhTopAppBar(
                title = "Data Setoran",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        },
    ) { innerPadding ->
        val uiStateSiswa by viewModel.homeUIState.collectAsState()
        BodyHome(
            itemSetoran = uiStateSiswa.listSetoran,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onSetoranClick = onDetailClick
        )
    }
}

@Composable
fun BodyHome(
    itemSetoran: List<SetoranData>,
    modifier: Modifier = Modifier,
    onSetoranClick: (String) -> Unit = {}
) {
   Column(
       horizontalAlignment = Alignment.CenterHorizontally,
       modifier = modifier
   ) {
       if (itemSetoran.isEmpty()) {
           Text(
               text = "Tidak ada data Setoran",
               textAlign = TextAlign.Center,
               style = MaterialTheme.typography.titleLarge
           )
       } else {
           ListSetoran(
               itemSetoran = itemSetoran,
               modifier = Modifier.padding(horizontal = 8.dp),
               onItemClick = { onSetoranClick(it.setoranID) }
           )
       }
   }
}

@Composable
fun ListSetoran(
    itemSetoran: List<SetoranData>,
    modifier: Modifier = Modifier,
    onItemClick: (SetoranData) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itemSetoran, key = { it.setoranID }) { setoranData ->
            DataSetoran(
                setoranData = setoranData,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(setoranData) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DataSetoran(
    setoranData: SetoranData,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {


            Text(
                text = "Juz " + setoranData.juz,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Surah " + setoranData.surat,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = "ayat " + setoranData.ayat,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = "Halaman " + setoranData.halaman,
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = "Tanggal " + setoranData.timestamp?.let { formatCustomDate(it) } ?: "No Date",
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

private fun formatCustomDate(timestamp: Any?): String {
    if (timestamp is com.google.firebase.Timestamp) {
        val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))
        return sdf.format(timestamp.toDate())
    }
    return ""
}