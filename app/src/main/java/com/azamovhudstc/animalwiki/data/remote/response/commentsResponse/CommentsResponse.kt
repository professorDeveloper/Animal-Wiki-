package com.azamovhudstc.animalwiki.data.remote.response.commentsResponse


import com.google.gson.annotations.SerializedName

data class CommentsResponse(
    @SerializedName("etag")
    val etag: String,
    @SerializedName("items")
    val items: List<ItemXX>,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("nextPageToken")
    val nextPageToken: String,
    @SerializedName("pageInfo")
    val pageInfo: PageInfoXX
)