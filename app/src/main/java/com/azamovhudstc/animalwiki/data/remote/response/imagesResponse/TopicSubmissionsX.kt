package com.azamovhudstc.animalwiki.data.remote.response.imagesResponse


import com.google.gson.annotations.SerializedName

data class TopicSubmissionsX(
    @SerializedName("animals")
    val animals: Animals,
    @SerializedName("nature")
    val nature: Nature,
    @SerializedName("people")
    val people: People,
    @SerializedName("spirituality")
    val spirituality: Spirituality,
    @SerializedName("wallpapers")
    val wallpapers: Wallpapers
)