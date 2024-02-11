package com.personalsprojects.androidcompose.ui.components.navigation

sealed class NavigationScreensSealed(val route: String){
    data object ScreenHeroList: NavigationScreensSealed("ScreenHeroList")
    data object ScreenFavs: NavigationScreensSealed("ScreenFavs")
    data object Screen3: NavigationScreensSealed("Screen3")

    data object ScreenDetail: NavigationScreensSealed("ScreenDetail")



}