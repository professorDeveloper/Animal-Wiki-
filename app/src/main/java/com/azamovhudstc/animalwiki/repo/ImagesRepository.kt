package com.azamovhudstc.animalwiki.repo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImageItem
import com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImagesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface ImagesRepository {
     fun getImagesByQuery(query: String, page: String): LiveData<PagingData<ImageItem>>
}