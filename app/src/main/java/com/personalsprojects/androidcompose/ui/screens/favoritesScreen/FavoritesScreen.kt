package com.personalsprojects.androidcompose.ui.screens.favoritesScreen


import androidx.compose.runtime.Composable
import com.personalsprojects.androidcompose.R
import com.personalsprojects.androidcompose.domain.Hero
import com.personalsprojects.androidcompose.domain.toLocal
import com.personalsprojects.androidcompose.states.FavoriteListState
import com.personalsprojects.androidcompose.states.HeroListState
import com.personalsprojects.androidcompose.ui.components.customLazyColumn.CustomLazyColumn
import com.personalsprojects.androidcompose.ui.components.heroCard.HeroCard


@Composable
fun FavoritesScreen(state :List<Hero>, favoritesViewModel: FavoritesViewModel, onPressHero: (Hero) -> Unit ) {
    CustomLazyColumn(background = R.drawable.wp10527461 , columnContent = {
        items(
            state.count(),
            itemContent = {
                HeroCard(hero = state[it], onPressHero = { onPressHero(state[it])} , onPressLike = {favoritesViewModel.onPressLike(
                    state[it].toLocal())})
            })
    })
}