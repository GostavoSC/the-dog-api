package gstv.dogapi.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import gstv.dogapi.domain.Breed
import gstv.dogapi.domain.DogImage
import gstv.dogapi.presentation.components.BaseScaffold
import gstv.dogapi.presentation.components.BaseTopAppBar
import gstv.dogapi.presentation.view_model.HomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = getViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.getRandomImages()
    }
    val state = viewModel.homeState.collectAsState().value

    BaseScaffold(topBar = { BaseTopAppBar(title = "Home") }) {
        HomeScreenContent(state.images)
    }
}

@Composable
private fun HomeScreenContent(images: List<DogImage>) {
    if (images.isNotEmpty()) {
        Column (modifier = Modifier.padding(vertical = 25.dp)){
            Image(
                painter = rememberAsyncImagePainter(images[0].url),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 40.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(
                        text = "By Category",
                        style = MaterialTheme.typography.h5,
                    )
                }

                Button(onClick = { /*TODO*/ }) {
                    Text(
                        text = "My Favourites",
                        style = MaterialTheme.typography.h5,
                    )
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                onClick = {

                }
            ) {
                Text(text = "See all", style = MaterialTheme.typography.h5)
            }
        }
    }
}


@Composable
private fun DogCard(breed: Breed) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp)
    ) {
        Column(Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
            Text(
                text = breed.name,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = breed.origin ?: "",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = breed.temperament ?: "",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
