package com.personalsprojects.androidcompose.ui.components.navigation



import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.personalsprojects.androidcompose.ui.screens.mainScreen.HeroListViewModel
import com.personalsprojects.androidcompose.ui.components.heroCard.HeroCard
import com.personalsprojects.androidcompose.R
import com.personalsprojects.androidcompose.domain.toLocal
import com.personalsprojects.androidcompose.ui.components.customLazyColumn.CustomLazyColumn
import com.personalsprojects.androidcompose.ui.screens.favoritesScreen.FavoritesScreen
import com.personalsprojects.androidcompose.ui.screens.favoritesScreen.FavoritesViewModel
import com.personalsprojects.androidcompose.ui.screens.heroDetailScreen.HeroDetailScreen
import com.personalsprojects.androidcompose.ui.screens.heroDetailScreen.HeroDetailScreenViewModel

@Composable
fun NavigationScreens(navController: NavHostController, heroesListviewModel: HeroListViewModel, favoritesViewModel: FavoritesViewModel, heroDetailScreenViewModel: HeroDetailScreenViewModel) {

    NavHost(navController = navController, startDestination = NavigationScreensSealed.ScreenHeroList.route){
        composable(NavigationScreensSealed.ScreenHeroList.route){
            val state by heroesListviewModel.state.collectAsState()
                CustomLazyColumn(background = R.drawable.wp10527461 , columnContent = {
                    items(
                        state.count(),
                        itemContent = {
                            HeroCard(hero = state[it],
                                onPressHero = { navController.navigate(NavigationScreensSealed.ScreenDetail.route + "/${state[it].id}")} ,
                                onPressLike = {heroesListviewModel.onPressLike(state[it].toLocal())})
                        })
                })

        }

        composable(NavigationScreensSealed.ScreenFavs.route){
            val state by favoritesViewModel.state.collectAsState()
            FavoritesScreen(favoritesViewModel = favoritesViewModel, state = state, onPressHero = { selectedHero ->
                navController.navigate(NavigationScreensSealed.ScreenDetail.route + "/${selectedHero.id}")
            }  )
        }

        composable(NavigationScreensSealed.Screen3.route){
            Text(text = "cScreen3")

        }

        composable(NavigationScreensSealed.ScreenDetail.route + "/{heroId}"){
            val heroId = it.arguments?.getString("heroId") ?: "error"
            HeroDetailScreen(heroId = heroId, heroDetailScreenViewModel, onPressBack = {navController.popBackStack()})

        }
    }
}