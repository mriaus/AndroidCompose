package com.personalsprojects.androidcompose.data

import com.personalsprojects.androidcompose.domain.Hero


interface Repository {
    suspend fun getHeroes(): List<Hero>
}