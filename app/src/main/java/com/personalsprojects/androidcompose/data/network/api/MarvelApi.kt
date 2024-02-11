package com.personalsprojects.androidcompose.data.network.api

import com.personalsprojects.androidcompose.data.network.model.HeroComicsRemote
import com.personalsprojects.androidcompose.data.network.model.HeroDetailRemote
import com.personalsprojects.androidcompose.data.network.model.HeroRemote
import com.personalsprojects.androidcompose.data.network.model.HeroSeriesRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi{
//Set the url
    @GET("v1/public/characters")
    suspend fun getHeroes(): HeroRemote

    @GET("v1/public/characters/{heroId}")
    suspend fun getHeroById(@Path("heroId") heroId: String): HeroDetailRemote

    @GET("v1/public/characters/{heroId}/comics")
    suspend fun getComicsByHeroId(@Path("heroId") heroId: String): HeroComicsRemote
    @GET("v1/public/characters/{heroId}/series")
    suspend fun getSeriesByHeroId(@Path("heroId") heroId: String): HeroSeriesRemote


}
