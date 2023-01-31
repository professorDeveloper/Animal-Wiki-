package com.azamovhudstc.animalwiki.data.remote.response.imagesResponse


import com.google.gson.annotations.SerializedName

data class Ancestry(
    @SerializedName("category")
    val category: Category,
    @SerializedName("subcategory")
    val subcategory: Subcategory,
    @SerializedName("type")
    val type: Type
)