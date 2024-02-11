package com.personalsprojects.androidcompose.states

import com.personalsprojects.androidcompose.domain.HeroDetail

sealed class HeroDetailState {
    data class Success(val hero: HeroDetail): HeroDetailState()
    data object Loading: HeroDetailState()

    data class Error(val error: String): HeroDetailState()
}