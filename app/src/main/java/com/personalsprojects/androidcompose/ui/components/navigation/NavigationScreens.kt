package com.personalsprojects.androidcompose.ui.components.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.personalsprojects.androidcompose.HeroListViewModel
import com.personalsprojects.androidcompose.states.HeroListState

@Composable
fun NavigationScreens(navController: NavHostController, viewModel: HeroListViewModel) {

    NavHost(navController = navController, startDestination = NavigationScreensSealed.Screen1.route){
        composable(NavigationScreensSealed.Screen1.route){
            val state by viewModel.state.collectAsState()
            when(state){
                is HeroListState.Success -> {
                    LazyColumn(Modifier.fillMaxSize()){
                        items((state as HeroListState.Success).heroes){
                            Text(text = it.name)
                        }
                    }
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