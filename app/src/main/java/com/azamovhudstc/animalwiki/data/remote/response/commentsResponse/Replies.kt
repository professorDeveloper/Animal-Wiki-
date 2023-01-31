package com.azamovhudstc.animalwiki.data.remote.response.commentsResponse


import com.azamovhudstc.animalwiki.data.remote.response.commentsResponse.Comment
import com.google.gson.annotations.SerializedName

data class Replies(
    @SerializedName("comments")
    val comments: List<Comment>
)