package com.example.tahfizhhub.ui.home

import androidx.compose.runtime.Composable
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