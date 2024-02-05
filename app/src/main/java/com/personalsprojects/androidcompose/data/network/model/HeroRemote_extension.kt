package com.personalsprojects.androidcompose.data.network.model

import com.personalsprojects.androidcompose.data.local.model.HeroLocal


fun List<HeroResult>.toLocal() = this.map {
    it.toLocal()
}

fun HeroResult.toLocal() = with(this){
    HeroLocal(id.toString(), name, "${thumbnail.path}${thumbnail.extension.name}", description, favorite = false )
}