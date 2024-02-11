package com.personalsprojects.androidcompose.data.network.model

import com.personalsprojects.androidcompose.domain.Serie

fun List<HeroSeriesRemoteResult>.toLocal() = this.map {
    it.toLocal()
}

fun HeroSeriesRemoteResult.toLocal() = with(this){
    Serie(title = title, photo = "${thumbnail.path}.${thumbnail.extension}")
}