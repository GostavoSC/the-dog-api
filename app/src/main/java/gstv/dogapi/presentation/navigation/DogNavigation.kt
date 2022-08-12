package gstv.dogapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import gstv.dogapi.core.utils.ChoicesEnum
import gstv.dogapi.presentation.screens.DogDetailsScreen
import gstv.dogapi.presentation.screens.DogListScreen
import gstv.dogapi.presentation.screens.HomeScreen
import gstv.dogapi.presentation.view_model.HomeViewModel
import org.koin.androidx.compose.getViewModel


sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object DogListScreen : Screen("dog-list")
    object DogDetailsScreen : Screen("dog-details")
}


@Composable
fun DogNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = getViewModel()
) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(viewModel) {
                navController.navigate(route = Screen.DogListScreen.route + "/$it")
            }
        }
        composable(Screen.DogListScreen.route + "/{choice}") {
            it.arguments?.getString("choice")?.let {
                DogListScreen(
                    choicesEnum = ChoicesEnum.fromValue(it.toInt()),
                    viewModel = viewModel
                ) {
                    viewModel.updateCurrentBreed(it)
                    navController.navigate(Screen.DogDetailsScreen.route)
                }
            }
        }
        composable(Screen.DogDetailsScreen.route) {
            DogDetailsScreen(viewModel)
        }
    }

}