package com.azamovhudstc.animalwiki.data.remote.api

import com.azamovhudstc.animalwiki.data.remote.response.channelResponse.ChannelResponse
import com.azamovhudstc.animalwiki.data.remote.response.commentsResponse.CommentsResponse
import com.azamovhudstc.animalwiki.data.remote.response.playListResponse.PlayListResponse
import com.azamovhudstc.animalwiki.data.remote.response.videoInfoResponse.VideoInfoResponse
import com.azamovhudstc.animalwiki.utils.consts.Constant
import retrofit2.Response
import retrofit2.http.*

interface YoutubeApi {
    @GET("playlistItems")
    suspend fun getPlayLists(
        @Query("key") key: String = Constant.API_KEY,
        @Query("part") part: String = Constant.PART,
        @Query("playlistId") playListId: String,
        @Query("maxResults") maxResults: Int
    ): Response<PlayListResponse>

    @GET("channels")
    suspend fun getChannel(
        @Query("key") key: String = Constant.API_KEY,
        @Query("part") part: String = Constant.CHANNEL_PART,
        @Query("id") channelId: String
    ): Response<ChannelResponse>

    @GET("videos")
    suspend fun getVideoInfo(
        @Query("key") key: String = Constant.API_KEY,
        @Query("part") part: String = Constant.VIDEO_INFO_PART,
        @Query("id") videoId: String
    ): Response<VideoInfoResponse>

    @GET("commentThreads")
    suspend fun getComments(
        @Query("key") key: String = Constant.API_KEY,
        @Query("part") part: String = Constant.COMMENTS_PART,
        @Query("maxResults") maxResults: String = "100",
        @Query("videoId") videoId: String
    ): Response<CommentsResponse>
}