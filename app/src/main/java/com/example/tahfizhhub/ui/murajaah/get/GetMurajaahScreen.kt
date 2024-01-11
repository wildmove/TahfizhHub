package com.example.tahfizhhub.ui.murajaah.get

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.example.tahfizhhub.model.MurajaahData
import com.example.tahfizhhub.navigasi.DestinasiNavigasi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tahfizhhub.ui.PenyediaViewModel
import com.example.tahfizhhub.ui.TahfizhTopAppBar
import com.example.tahfizhhub.ui.setoran.home.BodyHome
import java.text.SimpleDateFormat
import java.util.Locale

object DestinasiGetMurajaah : DestinasiNavigasi {
    override val route = "murajaahdata"
    override val titleRes = "Data Murajaah"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetMurajaahScreen(
    navigateBack: () -> Unit,
    navigateToMurajaahEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailMurajaahClick: (String) -> Unit = {},
    viewModel: GetMurajaahViewModel = viewModel(factory = PenyediaViewModel.Factory)

) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TahfizhTopAppBar(
                title = "Data Murajaah",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToMurajaahEntry,
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
        val uiStateMurajaah by viewModel.murajaahUIState.collectAsState()
        BodyMurajaah(
            itemMurajaah = uiStateMurajaah.listMurajaah,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onMurajaahClick = onDetailMurajaahClick
        )
    }
}

@Composable
fun BodyMurajaah(
    itemMurajaah: List<MurajaahData>,
    modifier: Modifier = Modifier,
    onMurajaahClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemMurajaah.isEmpty()) {
            Text(
                text = "Tidak ada data Murajaah",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListMurajaah(
                itemMurajaah = itemMurajaah,
                modifier = Modifier.padding(horizontal = 8.dp),
                onItemClick = { onMurajaahClick(it.murajaahID) }
            )
        }
    }
}

@Composable
fun ListMurajaah(
    itemMurajaah: List<MurajaahData>,
    modifier: Modifier = Modifier,
    onItemClick: (MurajaahData) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itemMurajaah, key = { it.murajaahID }) { murajaahData ->
            DataMurajaah(
                murajaahData = murajaahData,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(murajaahData) }
            )
            Spacer(modifier = Modifier.padding(8.dp))

        }
    }
}


@Composable
fun DataMurajaah(
    murajaahData: MurajaahData,
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
                text = "Juz " + murajaahData.juz,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Surah " + murajaahData.surat,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = "ayat " + murajaahData.ayat,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = "Halaman " + murajaahData.halaman,
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = "Tanggal " + murajaahData.timestamp?.let { formatCustomDate(it) } ?: "No Date",
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