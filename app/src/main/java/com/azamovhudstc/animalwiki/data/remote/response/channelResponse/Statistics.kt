package com.azamovhudstc.animalwiki.data.remote.response.channelResponse


import com.google.gson.annotations.SerializedName

data class Statistics(
    @SerializedName("hiddenSubscriberCount")
    val hiddenSubscriberCount: Boolean,
    @SerializedName("subscriberCount")
    val subscriberCount: String,
    @SerializedName("videoCount")
    val videoCount: String,
    @SerializedName("viewCount")
    val viewCount: String
)