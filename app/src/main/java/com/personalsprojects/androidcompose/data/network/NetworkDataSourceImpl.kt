package com.personalsprojects.androidcompose.data.network

import android.util.Log
import com.personalsprojects.androidcompose.data.network.api.MarvelApi
import com.personalsprojects.androidcompose.data.network.model.Data
import com.personalsprojects.androidcompose.data.network.model.HeroRemote
import com.personalsprojects.androidcompose.data.network.model.HeroRequestBody
import com.personalsprojects.androidcompose.data.network.model.HeroResult
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(private val api: MarvelApi) : NetworkDataSource {

    override suspend fun getHeroes(): HeroRemote {
            return api.getHeroes() }
}