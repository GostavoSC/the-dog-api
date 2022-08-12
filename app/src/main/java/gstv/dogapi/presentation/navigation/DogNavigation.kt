package gstv.dogapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import gstv.dogapi.presentation.screens.HomeScreen


sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
}


@Composable
fun DogNavigation() {
    NavHost(navController = rememberNavController(), startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }
    }
}