package com.personalsprojects.androidcompose.data

import com.personalsprojects.androidcompose.data.local.model.HeroLocal
import com.personalsprojects.androidcompose.domain.Comic
import com.personalsprojects.androidcompose.domain.Hero
import com.personalsprojects.androidcompose.domain.HeroDetail
import com.personalsprojects.androidcompose.domain.Serie
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


interface Repository {
    suspend fun getHeroes() : List<Hero>

    suspend fun updateHero(heroLocal: HeroLocal)

    suspend fun getNetworkHeroByID(heroId: String) : Flow<HeroDetail>

    suspend fun getNetworkSeriesByHeroID(heroId: String) : Flow<List<Serie>>

    suspend fun getNetworkComicsByHeroID(heroId: String) : Flow<List<Comic>>


    val heroesFlow: StateFlow<List<Hero>>
}