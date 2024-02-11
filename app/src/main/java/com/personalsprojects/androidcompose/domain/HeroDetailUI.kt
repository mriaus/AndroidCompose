package com.personalsprojects.androidcompose.domain

data class HeroDetailUI(
    val name: String,
    val photo: String,
    val description: String,
    val series: List<Serie>,
    val comics: List<Comic>
)