package com.personalsprojects.androidcompose.data.network.api

import com.personalsprojects.androidcompose.data.network.model.HeroRemote
import com.personalsprojects.androidcompose.data.network.model.HeroRequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MarvelApi{
//Set the url
    @GET("v1/public/characters")
    suspend fun getHeroes(): HeroRemote

}
