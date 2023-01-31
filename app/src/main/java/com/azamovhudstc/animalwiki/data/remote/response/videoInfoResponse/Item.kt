package com.azamovhudstc.animalwiki.data.remote.response.videoInfoResponse


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("contentDetails")
    val contentDetails: ContentDetails,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("player")
    val player: Player,
    @SerializedName("snippet")
    val snippet: Snippet,
    @SerializedName("statistics")
    val statistics: Statistics
)