package com.personalsprojects.androidcompose.ui.components.heroCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.personalsprojects.androidcompose.R
import com.personalsprojects.androidcompose.domain.Hero



@Composable
fun HeroCard(hero: Hero, modifier: Modifier? = null) {
    val colorStops = arrayOf(
        0.0f to Color.Black,
        0.2f to Color(0xAA000000),
        1f to Color.Transparent
    )
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

            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colorStops = colorStops
                        )
                    ))
            Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly){
                Text(text = hero.name,
                    color = Color.White
                )
                IconButton(onClick = { /*TODO*/ }, content ={ Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Like"
                )})
            }

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