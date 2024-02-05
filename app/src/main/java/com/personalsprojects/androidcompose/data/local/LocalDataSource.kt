package com.personalsprojects.androidcompose.data.local

import com.personalsprojects.androidcompose.data.local.model.HeroLocal

interface LocalDataSource {
    suspend fun getHeroes(): List<HeroLocal>
    suspend fun insertHeroes(heroes: List<HeroLocal>)

}