package com.azamovhudstc.animalwiki.viewmodels.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.animalwiki.data.remote.response.channelResponse.ChannelResponse
import com.azamovhudstc.animalwiki.data.remote.response.commentsResponse.CommentsResponse
import com.azamovhudstc.animalwiki.data.remote.response.videoInfoResponse.VideoInfoResponse
import com.azamovhudstc.animalwiki.usecases.VideoInfoScreenUseCase
import com.azamovhudstc.animalwiki.viewmodels.VideInfoScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideInfoScreenViewModelImp @Inject constructor(private val videoInfoScreenUseCase: VideoInfoScreenUseCase) :
    VideInfoScreenViewModel, ViewModel() {
    override val progressCommentsLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override val networkStateLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val channelInfoLiveData: MutableLiveData<ChannelResponse> = MutableLiveData()
    override val videoInfoLiveData: MutableLiveData<VideoInfoResponse> = MutableLiveData()
    override val commentsLiveData: MutableLiveData<CommentsResponse> = MutableLiveData()

    override fun getComments(videoId: String) {
        progressCommentsLiveData.value = true
        videoInfoScreenUseCase.getCommentsByVideoId(videoId).onEach {
            commentsLiveData.value = it
            progressCommentsLiveData.value = false
        }
            .launchIn(viewModelScope)
    }

    override fun networkState() {
        videoInfoScreenUseCase.networkConnectionState().onEach {
            if (!it) {
                networkStateLiveData.value = Unit
            }
        }
            .launchIn(viewModelScope)
    }

    override fun getFullInformation(channelId: String, videoId: String) {
        progressLiveData.value = true
        videoInfoScreenUseCase.getVideoFullInformation(videoId).onEach {
            videoInfoLiveData.value = it
            progressLiveData.value = false

        }.launchIn(viewModelScope)
    }
}