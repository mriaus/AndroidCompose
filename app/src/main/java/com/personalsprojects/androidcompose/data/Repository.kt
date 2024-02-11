package com.personalsprojects.androidcompose.data

import com.personalsprojects.androidcompose.data.local.model.HeroLocal
import com.personalsprojects.androidcompose.domain.Hero
import com.personalsprojects.androidcompose.domain.HeroDetail
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


interface Repository {
    suspend fun getHeroes() : List<Hero>

    suspend fun updateHero(heroLocal: HeroLocal)

    suspend fun getNetworkHeroByID(heroId: String) : HeroDetail

    val heroesFlow: StateFlow<List<Hero>>
}