package com.personalsprojects.androidcompose.ui.components.poster

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

@Composable
fun Poster(title: String, photo: String , modifier: Modifier? = null) {
    val colorStops = arrayOf(
        0.0f to Color.Black,
        0.2f to Color(0xAA000000),
        1f to Color.Transparent
    )

    ElevatedCard(
        modifier = modifier ?: Modifier.size(width =250.dp, height = 250.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter) {
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(photo)
                .crossfade(true)
                .build(),
                contentDescription = "Poster image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize())
            ShadowLayer(colorStops = colorStops)
            Text(text = title,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
        }

    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun Poster_Preview(){
    Poster(title = "spiderman", photo = "")
}