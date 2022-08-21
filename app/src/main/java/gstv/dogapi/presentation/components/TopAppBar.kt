package gstv.dogapi.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BaseTopAppBar(title: String, onClickButton: () -> Unit) {
    TopAppBar(
        actions = {
            IconButton(onClick = { onClickButton() }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "",
                    modifier = Modifier.size(35.dp)
                )
            }
        },
        title = {
            Text(text = title, style = MaterialTheme.typography.h5)
        }
    )
}

@Composable
fun FavouriteTopAppBar(title: String, onBackClicked: () -> Unit) {
    TopAppBar(navigationIcon = {
        IconButton(onClick = { onBackClicked() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
        }
    }, title = {
        Text(text = title, style = MaterialTheme.typography.h5)
    })
}