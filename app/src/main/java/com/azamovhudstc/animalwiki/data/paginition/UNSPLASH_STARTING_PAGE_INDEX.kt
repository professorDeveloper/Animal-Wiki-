package com.azamovhudstc.animalwiki.data.paginition

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.azamovhudstc.animalwiki.data.remote.api.UnsplashApi
import com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImageItem
import com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImagesResponse
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class UnsplashPagingSource(
    private val unsplashApi: UnsplashApi,
    private val query: String
) : PagingSource<Int,
        ImageItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int,ImageItem> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
        val response = unsplashApi.getQueryByImages(query=query, page = position, per_page = params.loadSize)
        val photos = response.body()?.imageItems!!

            LoadResult.Page(
                data =photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int,ImageItem>): Int? {
        TODO("Not yet implemented")
    }
}