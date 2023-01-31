package com.azamovhudstc.animalwiki.usecases

import com.azamovhudstc.animalwiki.data.local.models.WikiPostData
import com.azamovhudstc.animalwiki.data.remote.response.channelResponse.ChannelResponse
import com.azamovhudstc.animalwiki.data.remote.response.commentsResponse.CommentsResponse
import com.azamovhudstc.animalwiki.data.remote.response.videoInfoResponse.VideoInfoResponse
import kotlinx.coroutines.flow.Flow

interface VideoInfoScreenUseCase {
    fun networkConnectionState():Flow<Boolean>
    fun getCommentsByVideoId(videoId:String): Flow<CommentsResponse>
    fun getVideoFullInformation(videoId:String): Flow<VideoInfoResponse>

}