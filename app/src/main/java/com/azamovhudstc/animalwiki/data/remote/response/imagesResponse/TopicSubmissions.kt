package com.azamovhudstc.animalwiki.data.remote.response.imagesResponse


import com.google.gson.annotations.SerializedName

data class TopicSubmissions(
    @SerializedName("animals")
    val animals: Animals,
    @SerializedName("business-work")
    val businessWork: BusinessWork,
    @SerializedName("color-of-water")
    val colorOfWater: ColorOfWater,
    @SerializedName("nature")
    val nature: Nature,
    @SerializedName("textures-patterns")
    val texturesPatterns: TexturesPatterns,
    @SerializedName("wallpapers")
    val wallpapers: Wallpapers
)