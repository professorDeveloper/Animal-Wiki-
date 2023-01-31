package com.azamovhudstc.animalwiki.data.remote.response.commentsResponse


import com.google.gson.annotations.SerializedName

data class PageInfoXX(
    @SerializedName("resultsPerPage")
    val resultsPerPage: Int,
    @SerializedName("totalResults")
    val totalResults: Int
)