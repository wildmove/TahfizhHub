package com.example.tahfizhhub.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tahfizhhub.model.SetoranData
import com.example.tahfizhhub.navigasi.DestinasiNavigasi

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "setoran"
}

@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit
) {

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
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = setoranData.surat,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                )

                Text(
                    text = setoranData.halaman,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = setoranData.juz,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}