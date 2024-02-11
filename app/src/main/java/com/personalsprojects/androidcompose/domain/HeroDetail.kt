package com.personalsprojects.androidcompose.domain

import com.personalsprojects.androidcompose.data.network.model.Comics
import com.personalsprojects.androidcompose.data.network.model.Stories

data class HeroDetail(
    val name: String,
    val photo: String,
    val description: String
)