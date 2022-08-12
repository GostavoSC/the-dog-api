package gstv.dogapi.presentation.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun BaseTopAppBar(title: String) {
    TopAppBar() {
        Text(text = title, style = MaterialTheme.typography.h5)
    }
}