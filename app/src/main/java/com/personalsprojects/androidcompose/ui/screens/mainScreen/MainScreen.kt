package com.personalsprojects.androidcompose.ui.screens.mainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.personalsprojects.androidcompose.ui.components.navigation.NavigationScreens
import com.personalsprojects.androidcompose.ui.components.navigationBar.CustomNavigationBar
import com.personalsprojects.androidcompose.ui.screens.favoritesScreen.FavoritesViewModel

@Composable
fun MainScreen(heroListViewModel: HeroListViewModel, favoritesViewModel: FavoritesViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { CustomNavigationBar(navController = navController) }
    ) {
        Box(modifier = Modifier.padding(it)){
            NavigationScreens(navController = navController, heroesListviewModel = heroListViewModel, favoritesViewModel = favoritesViewModel)
        }
    }
}

/*
@Preview
@Composable
private fun MainScreen_Preview() {
    MainScreen()
}
*/