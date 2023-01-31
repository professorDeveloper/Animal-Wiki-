package com.azamovhudstc.animalwiki.usecases

import com.azamovhudstc.animalwiki.data.local.models.WikiPost
import kotlinx.coroutines.flow.Flow


interface PostsScreenUseCase{
    fun loadPosts(): Flow<ArrayList<WikiPost>>

}