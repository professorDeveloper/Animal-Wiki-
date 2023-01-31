package com.azamovhudstc.animalwiki.viewmodels.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azamovhudstc.animalwiki.data.model.ImageInfo
import com.azamovhudstc.animalwiki.viewmodels.ImageInfoScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class ImageInfoScreenViewModelImp : ImageInfoScreenViewModel, ViewModel() {
    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val shareLinkLiveData: MutableLiveData<String> = MutableLiveData()

    override fun back() {
        backLiveData.value = Unit
    }





    override fun shareLink() {
        TODO("Not yet implemented")
    }
}