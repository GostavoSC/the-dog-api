package gstv.dogapi.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import gstv.dogapi.core.utils.ChoicesEnum
import gstv.dogapi.domain.Breed
import gstv.dogapi.presentation.components.BaseScaffold
import gstv.dogapi.presentation.components.BaseTopAppBar
import gstv.dogapi.presentation.view_model.HomeViewModel

@Composable
fun DogListScreen(
    viewModel: HomeViewModel,
    onItemClicked: (Breed) -> Unit,
    onFavouritesClick: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getBreeds(choicesEnum = ChoicesEnum.SEE_ALL)
    }
    val state = viewModel.homeState.collectAsState().value

    BaseScaffold(topBar = {
        BaseTopAppBar(
            title = "Dog List",
            onClickButton = {
                onFavouritesClick()
            },
        )
    }) {
        DogListScreenContent(
            state.breeds,
            onItemClicked = {
                onItemClicked(it)
            },
        )
    }
}


@Composable
private fun DogListScreenContent(
    breeds: List<Breed>,
    onItemClicked: (Breed) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(12.dp)
    ) {
        items(breeds) {
            DogCard(breed = it) { breed ->
                onItemClicked(breed)
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DogCard(breed: Breed, onItemClicked: (Breed) -> Unit) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp),
        onClick = {
            onItemClicked(breed)
        }
    ) {
        Column(Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
            Text(
                text = breed.name,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = breed.temperament ?: "",
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
