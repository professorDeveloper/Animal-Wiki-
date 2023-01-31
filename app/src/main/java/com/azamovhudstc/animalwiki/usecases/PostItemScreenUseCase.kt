package com.azamovhudstc.animalwiki.usecases

import com.azamovhudstc.animalwiki.data.local.models.WikiPostData
import com.azamovhudstc.animalwiki.utils.enums.PostTypes
import kotlinx.coroutines.flow.Flow


interface PostItemScreenUseCase{
    fun loadPostItems(postTypes: PostTypes): Flow<ArrayList<WikiPostData>>

}