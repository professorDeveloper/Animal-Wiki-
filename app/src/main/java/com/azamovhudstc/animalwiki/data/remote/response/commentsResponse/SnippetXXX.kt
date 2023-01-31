package com.azamovhudstc.animalwiki.data.remote.response.commentsResponse


import com.azamovhudstc.animalwiki.data.remote.response.commentsResponse.TopLevelComment
import com.google.gson.annotations.SerializedName

data class SnippetXXX(
    @SerializedName("canReply")
    val canReply: Boolean,
    @SerializedName("isPublic")
    val isPublic: Boolean,
    @SerializedName("topLevelComment")
    val topLevelComment: TopLevelComment,
    @SerializedName("totalReplyCount")
    val totalReplyCount: Int,
    @SerializedName("videoId")
    val videoId: String
)