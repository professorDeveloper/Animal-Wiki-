package com.azamovhudstc.animalwiki.usecases.imp

import com.azamovhudstc.animalwiki.data.remote.response.channelResponse.ChannelResponse
import com.azamovhudstc.animalwiki.data.remote.response.commentsResponse.CommentsResponse
import com.azamovhudstc.animalwiki.data.remote.response.videoInfoResponse.VideoInfoResponse
import com.azamovhudstc.animalwiki.repo.NetworkRepository
import com.azamovhudstc.animalwiki.repo.VideoInfoRepository
import com.azamovhudstc.animalwiki.usecases.VideoInfoScreenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VideoInfoScreenUseCaseImp @Inject constructor(private val youtubeInfoRepository: VideoInfoRepository,private val networkRepository: NetworkRepository) :
    VideoInfoScreenUseCase {
    override fun networkConnectionState(): Flow<Boolean> = flow {

    }

    override fun getCommentsByVideoId(videoId: String): Flow<CommentsResponse> {
        return youtubeInfoRepository.getCommentsByVideoId(videoId)
    }

    override fun getVideoFullInformation(videoId: String): Flow<VideoInfoResponse> {
        return youtubeInfoRepository.getVideoFull(videoId = videoId)

    }
}