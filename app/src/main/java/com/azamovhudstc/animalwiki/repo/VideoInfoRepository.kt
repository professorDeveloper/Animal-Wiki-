package com.azamovhudstc.animalwiki.repo

import com.azamovhudstc.animalwiki.data.remote.response.commentsResponse.CommentsResponse
import com.azamovhudstc.animalwiki.data.remote.response.channelResponse.ChannelResponse
import com.azamovhudstc.animalwiki.data.remote.response.videoInfoResponse.VideoInfoResponse
import kotlinx.coroutines.flow.Flow

interface VideoInfoRepository {
    fun getChannelInformation(channelId: String): Flow<ChannelResponse>
    fun getCommentsByVideoId(videoId: String): Flow<CommentsResponse>
    fun getVideoFull(videoId: String):Flow<VideoInfoResponse>
}


