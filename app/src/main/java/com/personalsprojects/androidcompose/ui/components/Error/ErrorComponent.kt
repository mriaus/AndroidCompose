package com.personalsprojects.androidcompose.ui.components.Error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.personalsprojects.androidcompose.R

@Composable
fun ErrorComponent(){
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.wp10527461),
            contentDescription = "Your Image Description",
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.Center)
            ) {
                Text(
                    text = "Parece que ha habido un error",
                    style = MaterialTheme.typography.displayLarge.copy(color = Color(0xFFe62429)),
                )
            }

            Image(
                painter = painterResource(id = R.drawable.deadpool),
                contentDescription ="Deadpool image",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                contentScale = ContentScale.FillBounds
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun ErrorComponent_Preview(){
    ErrorComponent()
}