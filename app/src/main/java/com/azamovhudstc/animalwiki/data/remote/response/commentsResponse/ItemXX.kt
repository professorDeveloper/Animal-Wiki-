package com.azamovhudstc.animalwiki.data.remote.response.commentsResponse


import com.google.gson.annotations.SerializedName

data class ItemXX(
    @SerializedName("etag")
    val etag: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("replies")
    val replies: Replies,
    @SerializedName("snippet")
    val snippet: SnippetXXX
)