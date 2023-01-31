package com.azamovhudstc.animalwiki.data.local.database.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.azamovhudstc.animalwiki.data.model.GeneralModel
import com.azamovhudstc.animalwiki.data.remote.response.playListResponse.PlayListResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


    @Entity
    data class GeneralEntities(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        var title: String,
        var videosResponse: String,
    ) {

        fun toGeneralModel(): GeneralModel {
             var gson = Gson()
             var type = object : TypeToken<PlayListResponse>() {}.type
            var videosResponseData: PlayListResponse = gson.fromJson(videosResponse, type)
            return GeneralModel(title, videosResponseData)
        }

    }

