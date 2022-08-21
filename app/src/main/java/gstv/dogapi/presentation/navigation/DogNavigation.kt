package gstv.dogapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import gstv.dogapi.presentation.screens.DogDetailsScreen
import gstv.dogapi.presentation.screens.DogListScreen
import gstv.dogapi.presentation.screens.FavouriteImageScreen
import gstv.dogapi.presentation.view_model.HomeViewModel
import org.koin.androidx.compose.getViewModel


sealed class Screen(val route: String) {
    object DogListScreen : Screen("dog-list")
    object DogDetailsScreen : Screen("dog-details")
    object MyFavourites : Screen("favourites")
}

@Composable
fun DogNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = getViewModel()
) {
    NavHost(navController = navController, startDestination = Screen.DogListScreen.route) {
        composable(Screen.DogListScreen.route) {
            DogListScreen(
                viewModel = viewModel,
                onFavouritesClick = {
                    navController.navigate(Screen.MyFavourites.route)
                },
                onItemClicked = {
                    viewModel.updateCurrentBreed(it)
                    navController.navigate(Screen.DogDetailsScreen.route)
                })
        }
        composable(Screen.DogDetailsScreen.route) {
            DogDetailsScreen(viewModel)
        }

        composable(Screen.MyFavourites.route) {
            FavouriteImageScreen(homeViewModel = viewModel) {
                navController.popBackStack()
            }
        }

    }

}