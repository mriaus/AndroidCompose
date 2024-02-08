package com.personalsprojects.androidcompose.ui.components.heroCard


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.personalsprojects.androidcompose.domain.Hero
import com.personalsprojects.androidcompose.ui.components.shadowLayer.ShadowLayer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroCard(hero: Hero, modifier: Modifier? = null, onPressHero: () -> Unit, onPressLike: () -> Unit) {
    val colorStops = arrayOf(
        0.0f to Color.Black,
        0.2f to Color(0xAA000000),
        1f to Color.Transparent
    )

    ElevatedCard(
        modifier = modifier ?: Modifier.size(width =250.dp, height = 250.dp),
        elevation = CardDefaults.cardElevation(),
        onClick = {onPressHero()}
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

            ShadowLayer(colorStops = colorStops)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                ){
                Box(modifier = Modifier.weight(1f)){
                    Text(text = hero.name,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                IconButton(onClick = { onPressLike() },
                    content ={
                        Icon(
                            tint = Color(0xFFf56d74),
                            imageVector = if(hero.favorite){
                                Icons.Default.Favorite
                            }else {
                                Icons.Default.FavoriteBorder
                            },
                            contentDescription = "Like icon",
                            modifier = Modifier.size(40.dp, 40.dp)
                            )
                    }
                )
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
            "https://static.wikia.nocookie.net/marveldatabase/images/7/73/Phoenix_Resurrection_The_Return_of_Jean_Grey_Vol_1_1_Artgerm_Green_Costume_Variant_Textless.jpg",
            "a",
            true
        ),
        onPressHero = {},
        onPressLike = {}
    )
}