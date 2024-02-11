package com.personalsprojects.androidcompose.data.network

import com.personalsprojects.androidcompose.data.network.model.HeroDetailRemote
import com.personalsprojects.androidcompose.data.network.model.HeroRemote
import retrofit2.http.Path


interface NetworkDataSource {
    suspend fun getHeroes(): HeroRemote

    suspend fun getHeroById(heroId: String): HeroDetailRemote
}