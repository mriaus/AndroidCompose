package com.personalsprojects.androidcompose.data.network

import com.personalsprojects.androidcompose.data.network.api.MarvelApi
import com.personalsprojects.androidcompose.data.network.model.HeroDetailRemote
import com.personalsprojects.androidcompose.data.network.model.HeroRemote

import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(private val api: MarvelApi) : NetworkDataSource {

    override suspend fun getHeroes(): HeroRemote {
        return api.getHeroes()
    }

    override suspend fun getHeroById(heroId: String): HeroDetailRemote {
        return api.getHeroById(heroId)
    }
}