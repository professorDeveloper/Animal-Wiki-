package com.azamovhudstc.animalwiki.data.remote.response.playListResponse


import com.google.gson.annotations.SerializedName

data class ResourceId(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("videoId")
    val videoId: String
)