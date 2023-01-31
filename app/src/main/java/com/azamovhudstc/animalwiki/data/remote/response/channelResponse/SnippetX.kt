package com.azamovhudstc.animalwiki.data.remote.response.channelResponse


import com.google.gson.annotations.SerializedName

data class SnippetX(
    @SerializedName("country")
    val country: String,
    @SerializedName("customUrl")
    val customUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("localized")
    val localized: Localized,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("thumbnails")
    val thumbnails: ThumbnailsX,
    @SerializedName("title")
    val title: String
)