package com.personalsprojects.androidcompose.states

import com.personalsprojects.androidcompose.domain.Hero

sealed class HeroListState {
    data class Success(val heroes: List<Hero>): HeroListState()
    data object Loading: HeroListState()

    data class Error(val error: String): HeroListState()
}