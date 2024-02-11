package com.personalsprojects.androidcompose.ui.components.loading

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.personalsprojects.androidcompose.R

@Composable
fun ComplexLoading() {

    var showText by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(3000) // Espera 2 segundos (puedes ajustar el tiempo según tus necesidades)
        showText = true
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.wp10527461),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            CircularProgressIndicator(
                color = Color(0xFFe62429),
                modifier = Modifier.size(50.dp)
            )
            if (showText) {
                Text(text = "Si la carga tarda demasiado, compruebe su conexión")
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun ComplexLoading_Preview(){
    ComplexLoading()
}