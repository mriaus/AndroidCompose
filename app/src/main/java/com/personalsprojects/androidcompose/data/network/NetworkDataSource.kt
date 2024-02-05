package com.personalsprojects.androidcompose.data.network

import com.personalsprojects.androidcompose.data.network.model.HeroRemote


interface NetworkDataSource {
    suspend fun getHeroes(): HeroRemote
}