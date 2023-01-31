package com.azamovhudstc.animalwiki.data.remote.response.channelResponse


import com.google.gson.annotations.SerializedName

data class Localized(
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String
)