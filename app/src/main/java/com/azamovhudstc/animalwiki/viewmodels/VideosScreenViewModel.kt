package com.azamovhudstc.animalwiki.viewmodels

import android.icu.text.CaseMap
import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.animalwiki.data.model.GeneralModel

interface VideosScreenViewModel {
    fun getAllGeneralData()
    val quranPlayListsLiveData:MutableLiveData<ArrayList<GeneralModel>>
    val progressData:MutableLiveData<Boolean>
    val viewAllPlayList:MutableLiveData<GeneralModel>
    val openNavigationLiveData:MutableLiveData<Unit>
    fun openNavigation()
    fun viewAllPlayList(playListId:GeneralModel)
}