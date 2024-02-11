package com.personalsprojects.androidcompose.data.network

import com.personalsprojects.androidcompose.data.network.api.MarvelApi
import com.personalsprojects.androidcompose.data.network.model.HeroComicsRemote
import com.personalsprojects.androidcompose.data.network.model.HeroDetailRemote
import com.personalsprojects.androidcompose.data.network.model.HeroRemote
import com.personalsprojects.androidcompose.data.network.model.HeroSeriesRemote

import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(private val api: MarvelApi) : NetworkDataSource {

    override suspend fun getHeroes(): HeroRemote {
        return api.getHeroes()
    }

    override suspend fun getHeroById(heroId: String): HeroDetailRemote {
        return api.getHeroById(heroId)
    }

    override suspend fun getSeriesByHeroId(heroId: String): HeroSeriesRemote {
        return api.getSeriesByHeroId(heroId)
    }

    override suspend fun getComicsByHeroId(heroId: String) : HeroComicsRemote{
        return api.getComicsByHeroId(heroId)
    }
}