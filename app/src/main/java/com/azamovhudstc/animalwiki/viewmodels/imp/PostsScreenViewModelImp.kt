package com.azamovhudstc.animalwiki.viewmodels.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.animalwiki.data.local.models.WikiPost
import com.azamovhudstc.animalwiki.ui.screen.pages.PostsPage
import com.azamovhudstc.animalwiki.usecases.PostsScreenUseCase
import com.azamovhudstc.animalwiki.viewmodels.PostsScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostsScreenViewModelImp @Inject constructor(private val useCase: PostsScreenUseCase) :
    PostsScreenViewModel, ViewModel() {
    override val postClickLiveData: MutableLiveData<WikiPost> = MutableLiveData()
    override val loadAllPostsLiveData: MutableLiveData<ArrayList<WikiPost>> = MutableLiveData()

    override fun loadAllPosts() {
        useCase.loadPosts().onEach {
            loadAllPostsLiveData.value = it
        }.launchIn(viewModelScope)
    }

    override fun onClick(postsPage: WikiPost) {
        postClickLiveData.value = postsPage
    }
}