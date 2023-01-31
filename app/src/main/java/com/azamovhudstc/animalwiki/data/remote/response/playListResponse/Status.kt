package com.azamovhudstc.animalwiki.data.remote.response.playListResponse


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("privacyStatus")
    val privacyStatus: String
)