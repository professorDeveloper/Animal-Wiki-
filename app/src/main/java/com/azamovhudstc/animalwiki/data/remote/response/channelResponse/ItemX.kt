package com.azamovhudstc.animalwiki.data.remote.response.channelResponse


import com.google.gson.annotations.SerializedName

data class ItemX(
    @SerializedName("etag")
    val etag: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("snippet")
    val snippet: SnippetX,
    @SerializedName("statistics")
    val statistics: Statistics
)