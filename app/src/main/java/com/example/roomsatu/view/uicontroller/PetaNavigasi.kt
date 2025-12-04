package com.example.roomsatu.view.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomsatu.view.DetailSiswaScreen
import com.example.roomsatu.view.EditSiswaScreen
import com.example.roomsatu.view.EntrySiswaScreen
import com.example.roomsatu.view.HomeScreen
import com.example.roomsatu.view.route.DestinasiEntry
import com.example.roomsatu.view.route.DestinasiDetailSiswa
import com.example.roomsatu.view.route.DestinasiDetailSiswa.itemIdArg
import com.example.roomsatu.view.route.DestinasiEditSiswa
import com.example.roomsatu.view.route.DestinasiHome
import com.example.roomsatu.view.viewmodel.EntryViewModel
import com.example.roomsatu.view.viewmodel.provider.PenyediaViewModel


@Composable
fun SiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    HostNavigasi(navController=navController, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(navController = navController, startDestination = DestinasiHome.route, modifier= modifier)
    {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                navigateToItemDetail = { siswaId ->
                    navController.navigate("${DestinasiDetailSiswa.route}/$siswaId")
                },
                viewModel = viewModel(factory = PenyediaViewModel.Factory)
            )

        }
        composable(DestinasiEntry.route) {
            val viewModel: EntryViewModel = viewModel(factory = PenyediaViewModel.Factory)
            val uiStateSiswa = viewModel.uiStateSiswa  // Ambil state dari ViewModel

            EntrySiswaScreen(
                navigateBack = { navController.popBackStack() },
                uiStateSiswa = uiStateSiswa,
                viewModel = viewModel
            )
        }
        composable(route = DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(navArgument(name = itemIdArg) {
                type = NavType.IntType
            })
        ){
            DetailSiswaScreen(
                navigateToEditItem = { editId -> navController.navigate("edit/$editId") },
                navigateBack = {navController.navigateUp()})
        }
        composable(route = DestinasiEditSiswa.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditSiswa.itemIdArg){
                type = NavType.IntType })) {
            EditSiswaScreen(
                navigateBack = {navController.popBackStack()},
                onNavigateUp = {navController.navigateUp()})
        }
    }
}