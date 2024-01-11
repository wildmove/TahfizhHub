package com.example.tahfizhhub.ui.edit

import androidx.compose.ui.Modifier
import com.example.tahfizhhub.navigasi.DestinasiNavigasi

object EditSetoranDestination : DestinasiNavigasi {
    override val route = "item_edit_setoran"
    override val titleRes = "Edit Setoran"
    const val setoranId = "itemId"
    val routeWithArgs = "${EditSetoranDestination.route}/{$setoranId}"
}

fun EditSetoranScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {

}