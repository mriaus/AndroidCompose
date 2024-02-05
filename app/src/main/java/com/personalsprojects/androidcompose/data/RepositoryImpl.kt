package com.personalsprojects.androidcompose.data

import android.util.Log
import com.personalsprojects.androidcompose.data.local.LocalDataSource
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
        Log.d("HEROES", "ENTRA EN EL GETHEROES repos");

        val localHeroes = localDataSource.getHeroes()
        if(localHeroes.isEmpty()) {
            Log.d("HEROES", "ENTRA EN EL if get heroes repo");

            val remoteHeroes = networkDataSource.getHeroes()
            Log.d("HEROES", remoteHeroes.toString());
            localDataSource.insertHeroes(remoteHeroes.data.results.toLocal())
        }else{
            return localHeroes.toUI()
        }
        return localDataSource.getHeroes().toUI()
    }
}


