package com.personalsprojects.androidcompose.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.personalsprojects.androidcompose.domain.Hero


@Entity(tableName = "heroes")
data class HeroLocal (
    @PrimaryKey val id: String,
    val name: String,
    val photo: String,
    val description: String,
    var favorite: Boolean
    )

fun List<HeroLocal>.toUI(): List<Hero> = this.map {
    it.toUI()
}

fun HeroLocal.toUI(): Hero = with(this) {
    Hero(id, name, photo, description, favorite)
}