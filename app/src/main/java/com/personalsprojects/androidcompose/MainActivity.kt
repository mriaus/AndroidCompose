package com.personalsprojects.androidcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.personalsprojects.androidcompose.ui.screens.favoritesScreen.FavoritesViewModel
import com.personalsprojects.androidcompose.ui.screens.heroDetailScreen.HeroDetailScreenViewModel
import com.personalsprojects.androidcompose.ui.screens.mainScreen.HeroListViewModel
import com.personalsprojects.androidcompose.ui.screens.mainScreen.MainScreen
import com.personalsprojects.androidcompose.ui.theme.AndroidComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val heroListViewModel: HeroListViewModel by viewModels()
    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private val heroDetailViewModel: HeroDetailScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(heroListViewModel, favoritesViewModel,heroDetailViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidComposeTheme {
        Greeting("Android")
    }
}