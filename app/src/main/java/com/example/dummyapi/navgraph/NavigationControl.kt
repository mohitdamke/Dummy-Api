package com.example.dummyapi.navgraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dummyapi.presentation.ProductViewModel
import com.example.dummyapi.screens.DetailScreen
import com.example.dummyapi.screens.MainScreen


@Composable
fun Navigationcontrol(viewModel: ProductViewModel, paddingValues: PaddingValues) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "MainScreen") {

        composable(route = "MainScreen") {
            MainScreen(
                navController = navController, viewModel = viewModel
            )
        }

        composable(route = "Detail/{title}/{description}/{price}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("price") { type = NavType.StringType }
            )) {

            val title = it.arguments?.getString("title") ?: ""
            val description = it.arguments?.getString("description") ?: ""
            val price = it.arguments?.getString("price") ?: ""

            DetailScreen(title = title, description = description, price = price, navController = navController)

        }
    }
}