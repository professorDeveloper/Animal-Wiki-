package com.azamovhudstc.animalwiki.viewmodels.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.animalwiki.data.local.models.WikiPostData
import com.azamovhudstc.animalwiki.usecases.PostItemScreenUseCase
import com.azamovhudstc.animalwiki.utils.enums.PostTypes
import com.azamovhudstc.animalwiki.viewmodels.PostItemScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostItemsScreenViewModelImp @Inject constructor(private val useCase: PostItemScreenUseCase) :
    PostItemScreenViewModel, ViewModel() {
    override val onClickLiveData: MutableLiveData<WikiPostData>  = MutableLiveData()
    override val postItemsLiveData: MutableLiveData<ArrayList<WikiPostData>> = MutableLiveData()

    override fun getPostItems(postTypes: PostTypes) {
        useCase.loadPostItems(postTypes).onEach {
            postItemsLiveData.value = it
        }
            .launchIn(viewModelScope)
    }

    override fun onClick(wikiPostData: WikiPostData) {
        onClickLiveData.value=wikiPostData
    }

}