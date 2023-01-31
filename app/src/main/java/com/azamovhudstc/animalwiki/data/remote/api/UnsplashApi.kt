package com.azamovhudstc.animalwiki.data.remote.api

import com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImagesResponse
import com.azamovhudstc.animalwiki.utils.consts.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("search/photos")
    suspend fun getQueryByImages(
        @Query("client_id") clientId: String = Constant.API_KEY_UNSPLASH,
        @Query("per_page") per_page: Int = 15,
        @Query("order_by") order_by: String = "dark",
        @Query("page") page:Int,
        @Query("orientation") orientation: String = "portrait",
        @Query("query") query: String
    ): Response<ImagesResponse>
}