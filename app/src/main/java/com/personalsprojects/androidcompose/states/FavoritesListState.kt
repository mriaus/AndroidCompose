package com.personalsprojects.androidcompose.states

import com.personalsprojects.androidcompose.domain.Hero

sealed class FavoriteListState {
    data class Success(val heroes: List<Hero>): FavoriteListState()
    data object Loading: FavoriteListState()

    data class Error(val error: String): FavoriteListState()
}