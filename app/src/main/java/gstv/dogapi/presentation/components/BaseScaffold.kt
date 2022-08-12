package gstv.dogapi.presentation.components

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun BaseScaffold(topBar: @Composable () -> Unit, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            topBar()
        }
    ) {
        content()
    }
}