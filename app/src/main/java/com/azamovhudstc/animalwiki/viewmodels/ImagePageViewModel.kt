package com.azamovhudstc.animalwiki.viewmodels

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImagesResponse


interface ImagePageViewModel {
    fun getImagesByQuery(query: String,page:String)

}