package com.azamovhudstc.animalwiki.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImageItem
import com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImagesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface ImagePageScreenUseCase {
    fun getQueryByImage(query: String, page: String): LiveData<PagingData<ImageItem>>
}