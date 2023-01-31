package com.azamovhudstc.animalwiki.viewmodels

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.animalwiki.data.remote.response.playListResponse.PlayListResponse

interface ViewAllPlayListItemsScreenViewModel {
    var progressLiveData: MutableLiveData<Boolean>
    var getAllPlayListItemsLiveData: MutableLiveData<PlayListResponse>
    var backLiveData:MutableLiveData<Unit>
    fun getAllViewAllPlayListItems(playListId:String)
    fun backLiveData()
}