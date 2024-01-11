package com.example.tahfizhhub.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tahfizhhub.ui.add.AddScreen
import com.example.tahfizhhub.ui.add.DestinasiEntry
import com.example.tahfizhhub.ui.detail.DetailDestination
import com.example.tahfizhhub.ui.home.DestinasiHome
import com.example.tahfizhhub.ui.home.HomeScreen
import com.example.tahfizhhub.ui.menu.DestinasiMenu
import com.example.tahfizhhub.ui.menu.MenuScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiMenu.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = {
                navController.navigate(DestinasiEntry.route)
                },
                onDetailClick = { itemId ->
                    navController.navigate("${DetailDestination.route}/$itemId")
                    println("itemId: $itemId")
                },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(DestinasiEntry.route) {
            AddScreen(navigateBack = {
                navController.popBackStack()
            })

        }
        composable(DestinasiMenu.route) {
            MenuScreen(navigateToItemEntry = {
                navController.navigate(DestinasiEntry.route)
            }, navigateToMenu = {
                navController.navigate(DestinasiHome.route)
            })
        }
    }
}