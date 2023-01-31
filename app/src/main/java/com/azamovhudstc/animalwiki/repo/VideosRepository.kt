package com.azamovhudstc.animalwiki.repo

import com.azamovhudstc.animalwiki.data.model.GeneralModel
import com.azamovhudstc.animalwiki.data.remote.response.playListResponse.PlayListResponse
import kotlinx.coroutines.flow.Flow

interface VideosRepository {
    fun getQuranPlayListsById(playListId: String, maxResults: Int=200): Flow<PlayListResponse>
    fun getGeneralArrayList(): Flow<ArrayList<GeneralModel>>
}