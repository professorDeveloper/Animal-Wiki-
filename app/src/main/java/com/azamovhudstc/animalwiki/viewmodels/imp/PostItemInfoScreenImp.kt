package com.azamovhudstc.animalwiki.viewmodels.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azamovhudstc.animalwiki.data.local.models.WikiPostData
import com.azamovhudstc.animalwiki.ui.screen.posts.PostItemInfoScreen
import com.azamovhudstc.animalwiki.viewmodels.PostItemInfoScreenViewModel

class PostItemInfoScreenImp : PostItemInfoScreenViewModel, ViewModel() {
    override val showInfoLiveData: MutableLiveData<WikiPostData> = MutableLiveData()
    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val openTextSizeLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val openShareScreenLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun openShareScreen() {
        openShareScreenLiveData.value = Unit
    }

    override fun openTextSize() {
        openTextSizeLiveData.value = Unit
    }

    override fun back() {
        backLiveData.value=Unit
    }

    override fun showInfo(wikiPostData: WikiPostData) {
        showInfoLiveData.value=wikiPostData
    }
}