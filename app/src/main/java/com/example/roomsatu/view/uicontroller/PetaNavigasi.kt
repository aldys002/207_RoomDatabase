package com.example.roomsatu.view.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.roomsatu.view.route.DestinasIEntry
import com.example.roomsatu.view.route.DestinasiHome


@Composable
fun SiswaApp(navController: NavHostController = rememberNavController(), modifier: Modifier){
    HostNavigasi(navController=navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(navController = navController, startDestination = DestinasiHome.route, modifier= Modifier)
    {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasIEntry.route) },
            )
        }
        composable(DestinasIEntry.route) {
            EntrySiswaScreen(navigateBack = { navController.popBackStack() })
        }
    }
}