package com.azamovhudstc.animalwiki.usecases.imp

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.azamovhudstc.animalwiki.data.remote.api.UnsplashApi
import com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImageItem
import com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImagesResponse
import com.azamovhudstc.animalwiki.repo.ImagesRepository
import com.azamovhudstc.animalwiki.usecases.ImagePageScreenUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class
ImagePageScreenUseCaseImp @Inject constructor(private val imagesRepository: ImagesRepository): ImagePageScreenUseCase {
    override fun getQueryByImage(query: String, page: String): LiveData<PagingData<ImageItem>> {
        return imagesRepository.getImagesByQuery(query=query, page = page)
    }
}