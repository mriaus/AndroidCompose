package com.personalsprojects.androidcompose.data.network.model

data class HeroComicsRemote (
    val code: Long,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: HeroComicsRemoteData
)

data class HeroComicsRemoteData (
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    val results: List<HeroComicsRemoteResult>
)

data class HeroComicsRemoteResult (
    val title: String,
    val thumbnail: Thumbnail,
)















