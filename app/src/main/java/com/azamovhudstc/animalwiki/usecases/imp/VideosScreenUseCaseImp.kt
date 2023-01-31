package com.azamovhudstc.animalwiki.usecases.imp

import com.azamovhudstc.animalwiki.data.model.GeneralModel
import com.azamovhudstc.animalwiki.repo.NetworkRepository
import com.azamovhudstc.animalwiki.repo.VideosRepository
import com.azamovhudstc.animalwiki.usecases.VideosScreenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VideosScreenUseCaseImp @Inject constructor(
    private val videosRepository: VideosRepository,
    private val networkRepository: NetworkRepository
) :
    VideosScreenUseCase {
    override fun networkConnectionState(): Boolean  {
       return networkRepository.networkState()
    }

    override fun getAllGeneralData(): Flow<ArrayList<GeneralModel>> {
        return videosRepository.getGeneralArrayList()
    }
}