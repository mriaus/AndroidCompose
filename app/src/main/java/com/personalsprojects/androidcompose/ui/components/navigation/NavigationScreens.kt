package com.personalsprojects.androidcompose.ui.components.navigation


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.personalsprojects.androidcompose.HeroListViewModel
import com.personalsprojects.androidcompose.states.HeroListState
import com.personalsprojects.androidcompose.ui.components.heroCard.HeroCard

@Composable
fun NavigationScreens(navController: NavHostController, viewModel: HeroListViewModel) {

    NavHost(navController = navController, startDestination = NavigationScreensSealed.Screen1.route){
        composable(NavigationScreensSealed.Screen1.route){
            val state by viewModel.state.collectAsState()
            when(state){
                is HeroListState.Success -> {
                    LazyColumn(Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(20.dp),
                        content = {items(
                            ((state as HeroListState.Success).heroes.count()),
                            itemContent = {
                                HeroCard(hero = (state as HeroListState.Success).heroes[it])
                            })
                        }
                    )
                }
                is HeroListState.Loading -> {

                }
                else -> {
                    Text(text = "Error despuess de recibir los datos")
                }
            }
        }

        composable(NavigationScreensSealed.Screen2.route){
            Text(text = "cScreen2")

        }

        composable(NavigationScreensSealed.Screen3.route){
            Text(text = "cScreen3")

        }
    }
}