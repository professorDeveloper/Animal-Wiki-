package com.azamovhudstc.animalwiki.repo

import com.azamovhudstc.animalwiki.data.local.models.WikiPost
import com.azamovhudstc.animalwiki.data.local.models.WikiPostData
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun loadPosts(): Flow<ArrayList<WikiPost>>
   fun  loadTrees():Flow<ArrayList<WikiPostData>>
   fun  loadBirds():Flow<ArrayList<WikiPostData>>
   fun  loadAnimals():Flow<ArrayList<WikiPostData>>
   fun  loadFlowers():Flow<ArrayList<WikiPostData>>
}