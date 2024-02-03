package com.personalsprojects.androidcompose.ui.components.navigation

sealed class NavigationScreensSealed(val route: String){
    data object Screen1: NavigationScreensSealed("Screen1")
    data object Screen2: NavigationScreensSealed("Screen2")
    data object Screen3: NavigationScreensSealed("Screen3")

}