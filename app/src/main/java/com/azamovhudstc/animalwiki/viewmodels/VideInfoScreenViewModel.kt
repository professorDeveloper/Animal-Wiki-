package com.azamovhudstc.animalwiki.viewmodels

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.animalwiki.data.remote.response.channelResponse.ChannelResponse
import com.azamovhudstc.animalwiki.data.remote.response.commentsResponse.CommentsResponse
import com.azamovhudstc.animalwiki.data.remote.response.videoInfoResponse.VideoInfoResponse

interface VideInfoScreenViewModel {
    val progressCommentsLiveData:MutableLiveData<Boolean>
    val progressLiveData:MutableLiveData<Boolean>
    val networkStateLiveData:MutableLiveData<Unit>
    val channelInfoLiveData: MutableLiveData<ChannelResponse>
    val videoInfoLiveData: MutableLiveData<VideoInfoResponse>
    val commentsLiveData:MutableLiveData<CommentsResponse>
    fun getComments(videoId: String)
    fun networkState()
    fun getFullInformation(channelId: String, videoId: String)
}