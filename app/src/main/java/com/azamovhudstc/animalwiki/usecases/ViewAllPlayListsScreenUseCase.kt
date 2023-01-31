package com.azamovhudstc.animalwiki.usecases

import com.azamovhudstc.animalwiki.data.remote.response.playListResponse.PlayListResponse
import kotlinx.coroutines.flow.Flow

interface ViewAllPlayListsScreenUseCase {
    fun getAllPlayLists(playlistId:String): Flow<PlayListResponse>
}