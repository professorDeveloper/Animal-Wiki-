package com.azamovhudstc.animalwiki.usecases.imp

import com.azamovhudstc.animalwiki.data.remote.response.playListResponse.PlayListResponse
import com.azamovhudstc.animalwiki.repo.VideosRepository
import com.azamovhudstc.animalwiki.usecases.ViewAllPlayListsScreenUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ViewAllPlayListsScreenUseCaseImp @Inject constructor(private val repository: VideosRepository) :
    ViewAllPlayListsScreenUseCase {
    override fun getAllPlayLists(playlistId: String): Flow<PlayListResponse> {
        return repository.getQuranPlayListsById(playlistId)
    }
}