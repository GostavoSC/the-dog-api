package gstv.dogapi.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import gstv.dogapi.domain.FavouriteImage
import gstv.dogapi.presentation.components.BaseScaffold
import gstv.dogapi.presentation.components.FavouriteTopAppBar
import gstv.dogapi.presentation.view_model.HomeViewModel

@Composable
fun FavouriteImageScreen(homeViewModel: HomeViewModel, onBackClicked: () -> Unit) {
    LaunchedEffect(key1 = Unit) {
        homeViewModel.getMyFavourites()
    }
    val state = homeViewModel.homeState.collectAsState().value

    BaseScaffold(topBar = {
        FavouriteTopAppBar(title = "Favoritos") {
            onBackClicked()
        }
    }) {
        FavouriteImageScreenContent(state.favouriteImages)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun FavouriteImageScreenContent(images: List<FavouriteImage>) {

    // LazyColumn(content = {
    //     items(images) {
    //         val painter = rememberAsyncImagePainter(
    //             ImageRequest.Builder(LocalContext.current).data(data = it.image.url)
    //                 .apply(block = fun ImageRequest.Builder.() {
    //                     scale(Scale.FILL)
    //                 }).build()
    //         )
    //         Image(
    //             painter = painter,
    //             contentDescription = "",
    //             modifier = Modifier
    //                 .fillMaxWidth()
    //                 .height(500.dp)
    //         )
    //     }
    // })


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        content = {
            items(images) {
                if (it.image.url != null) {

                    val painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = it.image.url)
                            .apply(block = fun ImageRequest.Builder.() {
                                scale(Scale.FILL)
                            }).build()
                    )
                    Card(elevation = 2.dp, modifier = Modifier.padding(8.dp)) {

                        Image(
                            painter = painter,
                            contentDescription = "",
                            modifier = Modifier
                                .size(250.dp)
                                .padding(5.dp)
                        )
                    }
                }
            }
        }
    )
}