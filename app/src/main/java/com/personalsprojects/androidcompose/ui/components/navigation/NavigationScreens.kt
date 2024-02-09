package com.personalsprojects.androidcompose.ui.components.navigation


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.personalsprojects.androidcompose.ui.screens.mainScreen.HeroListViewModel
import com.personalsprojects.androidcompose.states.HeroListState
import com.personalsprojects.androidcompose.ui.components.heroCard.HeroCard
import com.personalsprojects.androidcompose.R
import com.personalsprojects.androidcompose.domain.toLocal
import com.personalsprojects.androidcompose.states.FavoriteListState
import com.personalsprojects.androidcompose.ui.components.customLazyColumn.CustomLazyColumn
import com.personalsprojects.androidcompose.ui.screens.favoritesScreen.FavoritesScreen
import com.personalsprojects.androidcompose.ui.screens.favoritesScreen.FavoritesViewModel

@Composable
fun NavigationScreens(navController: NavHostController, heroesListviewModel: HeroListViewModel, favoritesViewModel: FavoritesViewModel) {

    NavHost(navController = navController, startDestination = NavigationScreensSealed.Screen1.route){
        composable(NavigationScreensSealed.Screen1.route){
            val state by heroesListviewModel.state.collectAsState()
                CustomLazyColumn(background = R.drawable.wp10527461 , columnContent = {
                    items(
                        state.count(),
                        itemContent = {
                            HeroCard(hero = state[it],
                                onPressHero = { /*TODO*/} ,
                                onPressLike = {heroesListviewModel.onPressLike(state[it].toLocal())})
                        })
                })

        }

        composable(NavigationScreensSealed.Screen2.route){
            val state by favoritesViewModel.state.collectAsState()
            FavoritesScreen(favoritesViewModel = favoritesViewModel, state = state)
        }

        composable(NavigationScreensSealed.Screen3.route){
            Text(text = "cScreen3")

        }
    }
}