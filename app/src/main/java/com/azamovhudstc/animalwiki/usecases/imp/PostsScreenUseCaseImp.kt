package com.azamovhudstc.animalwiki.usecases.imp

import com.azamovhudstc.animalwiki.data.local.models.WikiPost
import com.azamovhudstc.animalwiki.repo.PostsRepository
import com.azamovhudstc.animalwiki.usecases.PostsScreenUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsScreenUseCaseImp @Inject constructor(private val repository: PostsRepository) :
    PostsScreenUseCase {
    override fun loadPosts(): Flow<ArrayList<WikiPost>> {

        return repository.loadPosts()
    }
}