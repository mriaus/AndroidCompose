package com.personalsprojects.androidcompose.domain

import com.personalsprojects.androidcompose.data.local.model.HeroLocal

data class Hero(
    val id: String,
    val name: String,
    val photo: String,
    val description: String,
    val favorite: Boolean
)

fun List<Hero>.toLocal(): List<HeroLocal> = this.map {
    it.toLocal()
}

fun Hero.toLocal(): HeroLocal = with(this) {
    HeroLocal(id, name, photo, description, favorite)
}