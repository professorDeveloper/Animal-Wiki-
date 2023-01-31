package com.azamovhudstc.animalwiki.viewmodels

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.animalwiki.data.local.models.WikiPostData

interface PostItemInfoScreenViewModel {
    val showInfoLiveData: MutableLiveData<WikiPostData>
    val backLiveData: MutableLiveData<Unit>
    val openTextSizeLiveData: MutableLiveData<Unit>
    val openShareScreenLiveData: MutableLiveData<Unit>
    fun openShareScreen()
    fun openTextSize()
    fun back()
    fun showInfo(wikiPostData: WikiPostData)
}