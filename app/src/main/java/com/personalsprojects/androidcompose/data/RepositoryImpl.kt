package com.personalsprojects.androidcompose.data

import android.util.Log
import com.personalsprojects.androidcompose.data.local.LocalDataSource
import com.personalsprojects.androidcompose.data.local.model.HeroLocal
import com.personalsprojects.androidcompose.data.local.model.toUI
import com.personalsprojects.androidcompose.data.network.NetworkDataSource
import com.personalsprojects.androidcompose.data.network.model.toLocal
import com.personalsprojects.androidcompose.domain.Hero
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
) : Repository {

    //Get heroes with cache
    override suspend fun getHeroes(): List<Hero> {
        val localHeroes = localDataSource.getHeroes()
        if(localHeroes.isEmpty()) {

            val remoteHeroes = networkDataSource.getHeroes()
            localDataSource.insertHeroes(remoteHeroes.data.results.toLocal())
        }else{
            return localHeroes.toUI()
        }
        return localDataSource.getHeroes().toUI()
    }

    //Update a hero in local. Used to like heroes
    override suspend fun updateHero(heroLocal: HeroLocal): List<Hero> {
        val localHero = localDataSource.getHeroById(heroLocal.id)

        if(localHero != null){
            localDataSource.updateHero(heroLocal)
        }
        return localDataSource.getHeroes().toUI()

    }
}


