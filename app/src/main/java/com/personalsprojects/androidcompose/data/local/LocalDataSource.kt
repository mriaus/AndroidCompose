package com.personalsprojects.androidcompose.data.local

import com.personalsprojects.androidcompose.data.local.model.HeroLocal
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getHeroes(): Flow<List<HeroLocal>>
    suspend fun insertHeroes(heroes: List<HeroLocal>)
    suspend fun updateHero(hero: HeroLocal)
    suspend fun getHeroById(heroId: String): HeroLocal?





}