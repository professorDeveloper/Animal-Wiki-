package com.azamovhudstc.animalwiki.data.model

import java.io.Serializable

data class ImageInfo(
    val width: String,
    val imageUrl:String,
    val height: String,
    val date: String,
    val likes: String,
    val authorName: String,
    val authorWebSite: String
):Serializable