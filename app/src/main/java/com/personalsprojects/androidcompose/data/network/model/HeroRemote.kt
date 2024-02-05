package com.personalsprojects.androidcompose.data.network.model

data class HeroRemote (
    val code: Long,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: Data
)

data class Data (
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    val results: List<HeroResult>
)

data class HeroResult (
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
)

data class Thumbnail (
    val path: String,
    val extension: Extension
)

enum class Extension {
    gif,
    jpg
}



