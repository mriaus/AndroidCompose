package com.personalsprojects.androidcompose.ui.components.customLazyColumn

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.personalsprojects.androidcompose.R
import com.personalsprojects.androidcompose.domain.toLocal
import com.personalsprojects.androidcompose.states.HeroListState
import com.personalsprojects.androidcompose.ui.components.heroCard.HeroCard


@Composable
fun CustomLazyColumn(background: Int?, columnContent: LazyListScope.() -> Unit ) {
    Box(modifier = Modifier.fillMaxSize()){
        if(background != null){
            Image(painter = painterResource(id = background ),
                contentDescription = "Background image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds)
        }
        LazyColumn(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(20.dp),
            content = columnContent
        )
    }
}

@Preview
@Composable
fun CustomLazyColumn_Preview(){
    CustomLazyColumn(background = R.drawable.wp10527461, {} )
}