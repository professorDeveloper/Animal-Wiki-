package com.azamovhudstc.animalwiki.data.model

import com.azamovhudstc.animalwiki.data.local.database.entites.GeneralEntities
import com.azamovhudstc.animalwiki.data.remote.response.playListResponse.PlayListResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class GeneralModel(val title: String, val quranResponse: PlayListResponse) {
    var gson = Gson()
    var type = object : TypeToken<PlayListResponse>() {}.type
    private val toJson: String = gson.toJson(quranResponse, type)
    fun toGenericEntity() = GeneralEntities(title = title, videosResponse = toJson)
}
