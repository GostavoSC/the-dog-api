package gstv.dogapi.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import gstv.dogapi.domain.Breed
import gstv.dogapi.domain.DogImage
import gstv.dogapi.presentation.view_model.HomeViewModel

@Composable
fun DogDetailsScreen(homeViewModel: HomeViewModel) {
    val state = homeViewModel.homeState.collectAsState().value
    LaunchedEffect(key1 = Unit) {
        homeViewModel.getBreedImages()
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Pager(images = state.images)
        DogDetailsScreenContent(breed = state.currentBreed, onFavoriteClick = {
            homeViewModel.favoriteImage()
        })
    }

}

@Composable
fun DogDetailsScreenContent(breed: Breed?, onFavoriteClick: () -> Unit) {
    val isFavorite = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${breed?.name}",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Medium
            )
            IconButton(
                onClick = {
                    isFavorite.value = true
                    onFavoriteClick()
                }) {
                Icon(
                    imageVector = if (isFavorite.value) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                    modifier = Modifier.size(40.dp),
                    contentDescription = ""
                )
            }
        }
        Text(
            text = "Tempo de vida: ${breed?.lifeSpan}",
            style = MaterialTheme.typography.h5
        )
        Text(
            text = "Temperamento: ${breed?.temperament}",
            style = MaterialTheme.typography.h5
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Pager(images: List<DogImage>) {
    val state = rememberPagerState()
    val imageUrl =
        remember { mutableStateOf<String?>("") }
    HorizontalPager(
        state = state,
        count = images.size, modifier = Modifier
            .height(550.dp)
            .fillMaxWidth()
    ) { page ->
        imageUrl.value = images[page].url

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.BottomCenter) {

                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = imageUrl.value)
                        .apply(block = fun ImageRequest.Builder.() {
                            scale(Scale.FILL)
                        }).build()
                )
                Image(
                    painter = painter, contentDescription = "", Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxSize(), contentScale = ContentScale.Crop
                )
            }
        }
    }

}