package com.personalsprojects.androidcompose.ui.components.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationScreens(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavigationScreensSealed.Screen1.route){
        composable(NavigationScreensSealed.Screen1.route){
            Text(text = "cScreen1")
        }

        composable(NavigationScreensSealed.Screen2.route){
            Text(text = "cScreen2")

        }

        composable(NavigationScreensSealed.Screen3.route){
            Text(text = "cScreen3")

        }
    }
}