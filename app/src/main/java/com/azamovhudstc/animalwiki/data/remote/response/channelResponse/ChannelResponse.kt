package com.azamovhudstc.animalwiki.data.remote.response.channelResponse


import com.google.gson.annotations.SerializedName

data class ChannelResponse(
    @SerializedName("etag")
    val etag: String,
    @SerializedName("items")
    val items: List<ItemX>,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("pageInfo")
    val pageInfo: PageInfoX
)