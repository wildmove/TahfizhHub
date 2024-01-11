package com.example.tahfizhhub.ui.murajaah.detail

import androidx.compose.ui.Modifier
import com.example.tahfizhhub.navigasi.DestinasiNavigasi

object DetailMurajaahDestination : DestinasiNavigasi {
    override val route = "murajaah_details"
    override val titleRes = "Detail Murajaah"
    const val murajaahId = "itemId"
    val routeWithArgs = "$route/{$murajaahId}"
}

fun DetailMurajaahScreen(
    avigateToEditMurajaah: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {

}