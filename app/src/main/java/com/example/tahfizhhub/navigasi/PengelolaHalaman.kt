package com.example.tahfizhhub.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tahfizhhub.ui.setoran.add.AddScreen
import com.example.tahfizhhub.ui.setoran.add.DestinasiEntry
import com.example.tahfizhhub.ui.setoran.detail.DetailDestination
import com.example.tahfizhhub.ui.setoran.detail.DetailScreen
import com.example.tahfizhhub.ui.setoran.edit.EditSetoranDestination
import com.example.tahfizhhub.ui.setoran.edit.EditSetoranScreen
import com.example.tahfizhhub.ui.setoran.home.DestinasiHome
import com.example.tahfizhhub.ui.setoran.home.HomeScreen
import com.example.tahfizhhub.ui.login.DestinasiLogin
import com.example.tahfizhhub.ui.login.LoginScreen
import com.example.tahfizhhub.ui.menu.DestinasiMenu
import com.example.tahfizhhub.ui.menu.MenuScreen
import com.example.tahfizhhub.ui.murajaah.add.AddMurajaahScreen
import com.example.tahfizhhub.ui.murajaah.add.DestinasiAddMurajaah
import com.example.tahfizhhub.ui.murajaah.get.DestinasiGetMurajaah
import com.example.tahfizhhub.ui.murajaah.get.GetMurajaahScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiMenu.route,
        modifier = Modifier
    ) {
        composable(DestinasiLogin.route) {
            LoginScreen(
                onNavToHomePage = {  },
                onNavToSignUpPage = { })
        }


        //Setoran
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
            MenuScreen(navigateToMurajaah = {
                navController.navigate(DestinasiGetMurajaah.route)
            }, navigateToSetoran = {
                navController.navigate(DestinasiHome.route)
            })
        }
        composable(
            route = DetailDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailDestination.setoranId) {
                type = NavType.StringType
            })
        ) {
            backStackEntry ->
            val setoranId = backStackEntry.arguments?.getString(DetailDestination.setoranId)
            setoranId?.let {
                DetailScreen(
                    navigateToEditItem = {
                        navController.navigate("${EditSetoranDestination.route}/$setoranId")
                        println("setoranId: $setoranId")
                    },
                    navigateBack = { navController.popBackStack() }
                )
            }
        }
        composable(
            route = EditSetoranDestination.routeWithArgs,
            arguments = listOf(navArgument(EditSetoranDestination.setoranId) {
                type = NavType.StringType
            })
        ) {backStackEntry ->
            val setoranId = backStackEntry.arguments?.getString(EditSetoranDestination.setoranId)
            setoranId?.let {
                EditSetoranScreen(
                    navigateBack = {navController.popBackStack()},
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }




        //Murajaah
        composable(DestinasiAddMurajaah.route) {
            AddMurajaahScreen(navigateBack = {
                navController.popBackStack()
            })
        }
        composable(DestinasiGetMurajaah.route) {
            GetMurajaahScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToMurajaahEntry = {
                    navController.navigate(DestinasiAddMurajaah.route)
                })
        }
    }
}