package com.personalsprojects.androidcompose.data.local.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.personalsprojects.androidcompose.data.local.model.HeroLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {

    @Query("Select * from heroes")
    fun getHeroes(): Flow<List<HeroLocal>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHeroes(heroes: List<HeroLocal>)

    @Update
    fun updateHero(hero: HeroLocal)

    @Query("SELECT * FROM heroes WHERE id = :heroId")
    fun getHeroById(heroId: String): HeroLocal?

}