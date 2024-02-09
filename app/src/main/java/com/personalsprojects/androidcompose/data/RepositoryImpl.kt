package com.personalsprojects.androidcompose.data

import android.util.Log
import com.personalsprojects.androidcompose.data.local.LocalDataSource
import com.personalsprojects.androidcompose.data.local.model.HeroLocal
import com.personalsprojects.androidcompose.data.local.model.toUI
import com.personalsprojects.androidcompose.data.network.NetworkDataSource
import com.personalsprojects.androidcompose.data.network.model.toLocal
import com.personalsprojects.androidcompose.domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
) : Repository {

    private val _heroesFlow = MutableStateFlow<List<Hero>>(emptyList())
    override val heroesFlow: StateFlow<List<Hero>> = _heroesFlow.asStateFlow()


    //Get heroes with cache
    override suspend fun getHeroes(): List<Hero> {
        val localHeroes = localDataSource.getHeroes().firstOrNull()

         if (localHeroes.isNullOrEmpty()) {
            // Si no hay héroes en la base de datos local, obtén los datos de la red
            val remoteHeroes = networkDataSource.getHeroes()
            localDataSource.insertHeroes(remoteHeroes.data.results.toLocal())
            localDataSource.getHeroes().map { it.toUI() }
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


