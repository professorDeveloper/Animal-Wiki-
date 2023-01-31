package com.azamovhudstc.animalwiki.usecases

import com.azamovhudstc.animalwiki.data.model.GeneralModel
import kotlinx.coroutines.flow.Flow

interface VideosScreenUseCase {
    fun networkConnectionState():Boolean
    fun getAllGeneralData(): Flow<ArrayList<GeneralModel>>
}