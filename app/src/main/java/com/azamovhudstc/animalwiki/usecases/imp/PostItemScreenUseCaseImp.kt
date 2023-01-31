package com.azamovhudstc.animalwiki.usecases.imp

import com.azamovhudstc.animalwiki.data.local.models.WikiPostData
import com.azamovhudstc.animalwiki.repo.PostsRepository
import com.azamovhudstc.animalwiki.usecases.PostItemScreenUseCase
import com.azamovhudstc.animalwiki.utils.enums.PostTypes
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostItemScreenUseCaseImp @Inject constructor(private val repository: PostsRepository) :
    PostItemScreenUseCase {
    override fun loadPostItems(postTypes: PostTypes): Flow<ArrayList<WikiPostData>> {

        return when (postTypes) {
            PostTypes.Trees -> repository.loadTrees()
            PostTypes.Flowers -> repository.loadFlowers()
            PostTypes.Animals -> repository.loadAnimals()
            else -> repository.loadBirds()
        }
    }
}