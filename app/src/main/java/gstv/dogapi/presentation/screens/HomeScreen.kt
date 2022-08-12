package gstv.dogapi.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import gstv.dogapi.domain.Breed
import gstv.dogapi.presentation.components.BaseScaffold
import gstv.dogapi.presentation.components.BaseTopAppBar
import gstv.dogapi.presentation.view_model.HomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = getViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.getBreeds()
    }
    val state = viewModel.homeState.collectAsState().value

    BaseScaffold(topBar = { BaseTopAppBar(title = "Home") }) {
        state.breeds?.let { HomeScreenContent(it) }
    }
}

@Composable
private fun HomeScreenContent(breeds: List<Breed>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        items(breeds) {
            DogCard(it)
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
