package com.personalsprojects.androidcompose.states

import com.personalsprojects.androidcompose.domain.HeroDetailUI

sealed class HeroDetailState {
    data class Success(val hero: HeroDetailUI): HeroDetailState()
    data object Loading: HeroDetailState()

    data class Error(val error: String): HeroDetailState()
}