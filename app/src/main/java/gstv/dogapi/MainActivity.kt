package gstv.dogapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import gstv.dogapi.presentation.navigation.DogNavigation
import gstv.dogapi.ui.theme.DogApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogApiTheme {
                DogNavigation()
            }
        }
    }
}