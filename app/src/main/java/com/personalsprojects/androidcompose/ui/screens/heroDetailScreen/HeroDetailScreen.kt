package com.personalsprojects.androidcompose.ui.screens.heroDetailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest

import com.personalsprojects.androidcompose.domain.Hero
import com.personalsprojects.androidcompose.states.HeroDetailState
import com.personalsprojects.androidcompose.ui.components.shadowLayer.ShadowLayer
import com.personalsprojects.androidcompose.ui.screens.mainScreen.HeroListViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroDetailScreen(heroId: String, viewModel: HeroDetailScreenViewModel , onPressBack: () -> Unit) {
    val colorStops = arrayOf(
        0.0f to Color.Black,
        0.2f to Color(0xAA000000),
        1f to Color.Transparent
    )

    LaunchedEffect(Unit){
        viewModel.getHero(heroId)
    }

    val state by viewModel.state.collectAsState()
    when(state){
        is HeroDetailState.Success -> {
            Box(modifier = Modifier.fillMaxSize()){
                Text(text = (state as HeroDetailState.Success).hero.name)
                AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                    .data((state as HeroDetailState.Success).hero.photo)
                    .crossfade(true)
                    .build(),
                    contentDescription = "Hero image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize())

                ShadowLayer(colorStops = colorStops)
            }
        }

        is HeroDetailState.Loading -> {
                Text(text = "Loading")
        }

        is HeroDetailState.Error -> {
            Text(text = "Error")

        }
    }




}


/*
@Preview(showSystemUi = true)
@Composable
fun HeroDetailScreen_Preview() {
    HeroDetailScreen(heroId = "")
}
*/