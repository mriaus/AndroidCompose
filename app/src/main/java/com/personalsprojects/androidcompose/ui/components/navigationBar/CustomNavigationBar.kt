package com.personalsprojects.androidcompose.ui.components.navigationBar

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.personalsprojects.androidcompose.R
import com.personalsprojects.androidcompose.data.network.NetworkDataSourceImpl
import com.personalsprojects.androidcompose.ui.components.navigation.NavigationScreensSealed


@Composable
fun CustomNavigationBar(navController: NavController) {

    var selectedScreen by remember {
        mutableStateOf(NavigationScreensSealed.Screen1.route)
    }

    NavigationBar(
        containerColor = Color(0xFFBCA8A1),

        content =
    {
        NavigationBarItem(
            selected = selectedScreen == NavigationScreensSealed.Screen1.route ,
            onClick = {
                selectedScreen = NavigationScreensSealed.Screen1.route
                navController.navigate(NavigationScreensSealed.Screen1.route)
            },
            icon = { Icon(Icons.AutoMirrored.Filled.List, "Show all") })
        NavigationBarItem(
            selected = selectedScreen == NavigationScreensSealed.Screen2.route,
            onClick = {selectedScreen = NavigationScreensSealed.Screen2.route
                navController.navigate(NavigationScreensSealed.Screen2.route)},
            icon = { Icon(Icons.Filled.Star, NavigationScreensSealed.Screen2.route) })
        /*
        NavigationBarItem(selectedScreen == NavigationScreensSealed.Screen3.route,
            onClick = { selectedScreen = NavigationScreensSealed.Screen3.route
                navController.navigate(NavigationScreensSealed.Screen3.route)},
            icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, "Exit") })
            */

    })
}

@Preview(showBackground = true)
@Composable
fun CustomNavigationBar_prev() {
    val navController = rememberNavController()

    CustomNavigationBar(navController)
}