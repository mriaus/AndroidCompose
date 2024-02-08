package com.personalsprojects.androidcompose.ui.components.shadowLayer

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ShadowLayer(colorStops: Array<Pair<Float, Color>>){
    Box(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = colorStops
                )
            ))
}

@Preview
@Composable
fun ShadowLayer_Preview(){
    ShadowLayer(colorStops = arrayOf(
        0.0f to Color(0xFF8B0000),
        0.2f to Color(0xAA8B0000),
        1f to Color.Transparent ))
}