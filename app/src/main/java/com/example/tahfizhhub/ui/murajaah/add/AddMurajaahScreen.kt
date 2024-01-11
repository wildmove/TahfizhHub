package com.example.tahfizhhub.ui.murajaah.add

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tahfizhhub.navigasi.DestinasiNavigasi

object DestinasiAddMurajaah : DestinasiNavigasi {
    override val route = "add_murajaah"
    override val titleRes = "Tambah Data Murajaah"
}

@Composable
fun AddMurajaahScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {

}

