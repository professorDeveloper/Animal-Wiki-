package com.azamovhudstc.animalwiki.data.remote.response.channelResponse


import com.google.gson.annotations.SerializedName

data class ThumbnailsX(
    @SerializedName("default")
    val default: DefaultX,
    @SerializedName("high")
    val high: HighX,
    @SerializedName("medium")
    val medium: MediumX
)