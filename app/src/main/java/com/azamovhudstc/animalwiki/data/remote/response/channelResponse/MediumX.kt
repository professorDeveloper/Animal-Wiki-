package com.azamovhudstc.animalwiki.data.remote.response.channelResponse


import com.google.gson.annotations.SerializedName

data class MediumX(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)