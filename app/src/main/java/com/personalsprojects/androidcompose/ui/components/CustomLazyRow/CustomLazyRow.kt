package com.personalsprojects.androidcompose.ui.components.CustomLazyRow

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.personalsprojects.androidcompose.R

@Composable
fun CustomLazyRow(background: Int?, rowContent: LazyListScope.() -> Unit , modifier: Modifier? = null) {
    Box(modifier = modifier ?: Modifier.fillMaxSize()){
        if(background != null){
            Image(painter = painterResource(id = background ),
                contentDescription = "Background image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds)
        }
        LazyRow(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(20.dp),
            content = rowContent
        )
    }
}

@Preview
@Composable
fun CustomLazyRow_Preview(){
    CustomLazyRow(background = R.drawable.wp10527461, {} )
}