package com.personalsprojects.androidcompose.ui.components.heroCard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.personalsprojects.androidcompose.domain.Hero



@Composable
fun HeroCard(hero: Hero, modifier: Modifier? = null) {
    ElevatedCard(
        modifier = modifier ?: Modifier.size(width =250.dp, height = 250.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Box(Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter) {
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(hero.photo)
                .crossfade(true)
                .build(),
                contentDescription = "Hero image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize())
            Text(text = hero.name)
        }

    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun HeroCard_Preview(){
    HeroCard(
        hero = Hero(
            "123",
            "Phoenix",
            "https://static.wikia.nocookie.net/marveldatabase/images/7/73/Phoenix_Resurrection_The_Return_of_Jean_Grey_Vol_1_1_Artgerm_Green_Costume_Variant_Textless.jpg/revision/latest?cb=20171031223546",
            "a",
            true
        )
    )
}