package com.personalsprojects.androidcompose.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.personalsprojects.androidcompose.data.local.model.HeroLocal

@Database(entities = [HeroLocal::class], version =1)
abstract class HeroDatabase: RoomDatabase() {
    abstract fun heroDao(): HeroDao
}