package com.personalsprojects.androidcompose.data.network.model

import com.personalsprojects.androidcompose.domain.Comic


fun List<HeroComicsRemoteResult>.toLocal() = this.map {
    it.toLocal()
}

fun HeroComicsRemoteResult.toLocal() = with(this){
    Comic(title = title, photo = "${thumbnail.path}.${thumbnail.extension}")
}