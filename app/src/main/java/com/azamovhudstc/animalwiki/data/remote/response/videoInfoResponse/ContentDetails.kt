package com.azamovhudstc.animalwiki.data.remote.response.videoInfoResponse


import com.google.gson.annotations.SerializedName

data class ContentDetails(
    @SerializedName("caption")
    val caption: String,
    @SerializedName("contentRating")
    val contentRating: ContentRating,
    @SerializedName("definition")
    val definition: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("licensedContent")
    val licensedContent: Boolean,
    @SerializedName("projection")
    val projection: String
)