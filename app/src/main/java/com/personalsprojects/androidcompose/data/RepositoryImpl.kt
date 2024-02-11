package com.personalsprojects.androidcompose.data

import com.personalsprojects.androidcompose.data.local.LocalDataSource
import com.personalsprojects.androidcompose.data.local.model.HeroLocal
import com.personalsprojects.androidcompose.data.local.model.toUI
import com.personalsprojects.androidcompose.data.network.NetworkDataSource
import com.personalsprojects.androidcompose.data.network.model.toDetail
import com.personalsprojects.androidcompose.data.network.model.toLocal
import com.personalsprojects.androidcompose.domain.Comic
import com.personalsprojects.androidcompose.domain.Hero
import com.personalsprojects.androidcompose.domain.HeroDetail
import com.personalsprojects.androidcompose.domain.Serie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
) : Repository {
    //See how to change this
    private val _heroesFlow = MutableStateFlow<List<Hero>>(emptyList())
    override val heroesFlow: StateFlow<List<Hero>> = _heroesFlow.asStateFlow()


    //Get heroes with cache
    override suspend fun getHeroes(): List<Hero> {
        val localHeroes = localDataSource.getHeroes().firstOrNull()

         if (localHeroes.isNullOrEmpty()) {
             try{
                 val remoteHeroes = networkDataSource.getHeroes()
                 localDataSource.insertHeroes(remoteHeroes.data.results.toLocal())
             } catch(e: Exception) {
                 _heroesFlow.value = listOf()
             }

        }
        reloadState()
        heroesFlow.collect()

    return heroesFlow.value
    }

    //Update a hero in local. Used to like heroes
    override suspend fun updateHero(heroLocal: HeroLocal){
        val localHero = localDataSource.getHeroById(heroLocal.id)

        if(localHero != null){
            localDataSource.updateHero(heroLocal)
        }
        reloadState()
    }

    override suspend fun getNetworkHeroByID(heroId: String): Flow<HeroDetail> = flow {
        val remoteHero = networkDataSource.getHeroById(heroId)
        emit(remoteHero.data.results.toDetail()[0])
    }

    override suspend fun getNetworkSeriesByHeroID(heroId: String): Flow<List<Serie>> = flow {
        val remoteSeries = networkDataSource.getSeriesByHeroId(heroId)
        emit(remoteSeries.data.results.toLocal())
    }

    override suspend fun getNetworkComicsByHeroID(heroId: String): Flow<List<Comic>> = flow {
        val remoteSeriesComics = networkDataSource.getComicsByHeroId(heroId)
        emit(remoteSeriesComics.data.results.toLocal())
    }

    private suspend fun reloadState(){
        localDataSource.getHeroes()
            .map { localHeroes ->
                localHeroes.map { localHero -> localHero.toUI() }
            }
            .collectLatest {
                _heroesFlow.value = it
            }
    }
}


