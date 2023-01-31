package com.azamovhudstc.animalwiki.repo.imp

import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.data.local.models.WikiPost
import com.azamovhudstc.animalwiki.repo.PostsRepository
import com.azamovhudstc.animalwiki.utils.consts.LoadPosts
import com.azamovhudstc.animalwiki.utils.enums.PostTypes
import com.azamovhudstc.animalwiki.data.local.models.WikiPostData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostsRepositoryImp @Inject constructor() : PostsRepository {
    override fun loadPosts(): Flow<ArrayList<WikiPost>> = flow {
        var arrayList = ArrayList<WikiPost>()
        arrayList.add(WikiPost(PostTypes.Animals, "Animals", R.drawable.animals))
        arrayList.add(WikiPost(PostTypes.Birds, "Birds", R.drawable.get))
        arrayList.add(WikiPost(PostTypes.Flowers, "Flowers", R.drawable.gul))
        arrayList.add(WikiPost(PostTypes.Trees, "Trees", R.drawable.treesaaa))
        emit(arrayList)
    }.flowOn(Dispatchers.IO)


    override fun loadTrees(): Flow<ArrayList<WikiPostData>> = flow {
        emit(LoadPosts.loadTrees())
    }.flowOn(Dispatchers.IO)

    override fun loadBirds(): Flow<ArrayList<WikiPostData>> = flow {
        emit(LoadPosts.loadBirds())
    }.flowOn(Dispatchers.IO)

    override fun loadAnimals(): Flow<ArrayList<WikiPostData>> = flow {
        emit(LoadPosts.loadAnimals())
    }.flowOn(Dispatchers.IO)

    override fun loadFlowers(): Flow<ArrayList<WikiPostData>> = flow {
        emit(LoadPosts.loadFlowers())
    }.flowOn(Dispatchers.IO)
}