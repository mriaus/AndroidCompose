package com.personalsprojects.androidcompose.data.network.model

data class HeroSeriesRemote (
    val code: Long,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: HeroSeriesRemoteData
)

data class HeroSeriesRemoteData (
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    val results: List<HeroSeriesRemoteResult>
)

data class HeroSeriesRemoteResult (
    val id: Long,
    val title: String,
    val thumbnail: Thumbnail,
    val stories: Stories,

)







