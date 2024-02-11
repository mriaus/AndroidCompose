package com.personalsprojects.androidcompose.data.network.model

import com.personalsprojects.androidcompose.domain.HeroDetail

    fun List<Result>.toDetail() = this.map {
        it.toDetail()
    }

    fun Result.toDetail() = with(this){
        HeroDetail(name, "${thumbnail.path}.${thumbnail.extension.name}",description )
    }
