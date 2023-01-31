package com.azamovhudstc.animalwiki.viewmodels

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.animalwiki.data.model.ImageInfo

interface ImageInfoScreenViewModel {
    val backLiveData: MutableLiveData<Unit>
    val shareLinkLiveData: MutableLiveData<String>
    fun back()

    fun shareLink()
}