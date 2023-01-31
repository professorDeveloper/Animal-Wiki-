package com.azamovhudstc.animalwiki.data.remote.response.imagesResponse


import com.google.gson.annotations.SerializedName

data class Nature(
    @SerializedName("approved_on")
    val approvedOn: String,
    @SerializedName("status")
    val status: String
)