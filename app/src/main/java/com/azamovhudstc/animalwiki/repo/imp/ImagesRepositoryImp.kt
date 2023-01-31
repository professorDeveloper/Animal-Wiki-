package com.azamovhudstc.animalwiki.repo.imp

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.azamovhudstc.animalwiki.data.paginition.UnsplashPagingSource
import com.azamovhudstc.animalwiki.data.remote.api.UnsplashApi
import com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImageItem
import com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImagesResponse
import com.azamovhudstc.animalwiki.repo.ImagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ImagesRepositoryImp @Inject constructor(private val imagesApi: UnsplashApi) :
    ImagesRepository {
    override fun getImagesByQuery(query: String, page: String): LiveData<PagingData<ImageItem>> = Pager(config = PagingConfig(pageSize = 20, maxSize = 100), pagingSourceFactory = {UnsplashPagingSource(unsplashApi = imagesApi,query)})
            .liveData
}