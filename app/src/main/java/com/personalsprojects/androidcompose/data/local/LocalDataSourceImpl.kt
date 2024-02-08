package com.personalsprojects.androidcompose.data.local

import android.util.Log
import com.personalsprojects.androidcompose.data.local.dataBase.HeroDao
import com.personalsprojects.androidcompose.data.local.model.HeroLocal
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: HeroDao) : LocalDataSource {
    override suspend fun getHeroes(): List<HeroLocal> {
        return dao.getHeroes()
    }

    override suspend fun insertHeroes(heroes: List<HeroLocal>) {
        return dao.insertHeroes(heroes)
    }

    override suspend fun updateHero(hero: HeroLocal) {
        dao.updateHero(hero)
    }

    override suspend fun getHeroById(heroId: String): HeroLocal? {
       return dao.getHeroById(heroId)

    }
}