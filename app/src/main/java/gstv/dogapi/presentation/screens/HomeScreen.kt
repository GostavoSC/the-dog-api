package gstv.dogapi.presentation.screens

import androidx.compose.runtime.Composable
import gstv.dogapi.presentation.components.BaseScaffold
import gstv.dogapi.presentation.view_model.HomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = getViewModel()) {
    viewModel.getBreeds()
    BaseScaffold(topBar = { /*TODO*/ }) {
        HomeScreenContent()
    }
}

@Composable
private fun HomeScreenContent() {

}