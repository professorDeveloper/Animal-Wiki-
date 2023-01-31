package com.azamovhudstc.animalwiki.repo.imp

import com.azamovhudstc.animalwiki.data.remote.api.YoutubeApi
import com.azamovhudstc.animalwiki.data.remote.response.channelResponse.ChannelResponse
import com.azamovhudstc.animalwiki.data.remote.response.commentsResponse.CommentsResponse
import com.azamovhudstc.animalwiki.data.remote.response.videoInfoResponse.VideoInfoResponse
import com.azamovhudstc.animalwiki.repo.VideoInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VideoInfoRepositoryImp @Inject constructor(private val youtubeApi: YoutubeApi) :
    VideoInfoRepository {
    override fun getChannelInformation(channelId: String): Flow<ChannelResponse> = flow {
        var channelData = youtubeApi.getChannel(channelId = channelId)

        if (channelData.isSuccessful) {
            emit(channelData.body()!!)
        }

    }.flowOn(Dispatchers.IO)

    override fun getCommentsByVideoId(videoId: String): Flow<CommentsResponse> =
        flow {
            var commentsData = youtubeApi.getComments(videoId = videoId)

            if (commentsData.isSuccessful) {
                emit(commentsData.body()!!)
            }
        }
            .flowOn(Dispatchers.IO)

    override fun getVideoFull(videoId: String): Flow<VideoInfoResponse> = flow {
        var videoInfoData = youtubeApi.getVideoInfo(videoId = videoId)
        if (videoInfoData.isSuccessful) {
            emit(videoInfoData.body()!!)
        }
    }
        .flowOn(Dispatchers.IO)
}