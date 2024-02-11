package com.personalsprojects.androidcompose.ui.screens.heroDetailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.personalsprojects.androidcompose.states.HeroDetailState
import com.personalsprojects.androidcompose.ui.components.CustomLazyRow.CustomLazyRow
import com.personalsprojects.androidcompose.ui.components.Error.ErrorComponent
import com.personalsprojects.androidcompose.ui.components.loading.ComplexLoading
import com.personalsprojects.androidcompose.ui.components.poster.Poster
import com.personalsprojects.androidcompose.ui.components.shadowLayer.ShadowLayer


@Composable
fun HeroDetailScreen(heroId: String, viewModel: HeroDetailScreenViewModel) {
    val colorStops = arrayOf(
        0.0f to Color.Black,
        0.2f to Color(0xAA000000))

    LaunchedEffect(Unit){
        viewModel.obtainAllDetail(heroId)
    }

    //Maybe bad flow
    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetState()
        }
    }

    val state by viewModel.state.collectAsState()
    when(state) {
        is HeroDetailState.Success -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter

            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data((state as HeroDetailState.Success).hero.photo)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Hero image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
                ShadowLayer(colorStops = colorStops)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = (state as HeroDetailState.Success).hero.name,
                        style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
                    )

                    //Series (Change to https://m3.material.io/components/carousel/overview )
                    if ((state as HeroDetailState.Success).hero.series.isNotEmpty()) {
                        Column {
                            Text(
                                text = "SERIES",
                                style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                            )
                            CustomLazyRow(
                                background = null, rowContent = {
                                    items(
                                        (state as HeroDetailState.Success).hero.series.count(),
                                        itemContent = {
                                            Poster(
                                                title = (state as HeroDetailState.Success).hero.series[it].title,
                                                photo = (state as HeroDetailState.Success).hero.series[it].photo,
                                                Modifier.size(150.dp, 200.dp)
                                            )
                                        })
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }
                    }


                    //Comics
                    if ((state as HeroDetailState.Success).hero.comics.isNotEmpty()) {
                        Column {
                            Text(
                                text = "COMICS",
                                style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                            )
                            CustomLazyRow(
                                background = null, rowContent = {
                                    items(
                                        (state as HeroDetailState.Success).hero.comics.count(),
                                        itemContent = {
                                            Poster(
                                                title = (state as HeroDetailState.Success).hero.comics[it].title,
                                                photo = (state as HeroDetailState.Success).hero.comics[it].photo,
                                                Modifier.size(150.dp, 200.dp)
                                            )
                                        })
                                }, modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }
                    }

                    //Description
                    Column {
                        Text(
                            text = "Description",
                            Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                        )
                        if ((state as HeroDetailState.Success).hero.description.isNotEmpty()) {
                            Text(
                                text = (state as HeroDetailState.Success).hero.description,
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
                            )
                        } else {
                            Text(
                                text = "No hay descripción para este heroe",
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
                            )
                        }
                    }

                }

            }
        }

        is HeroDetailState.Loading -> {
            ComplexLoading()
        }

        is HeroDetailState.Error -> {
            ErrorComponent()
        }
    }

}


