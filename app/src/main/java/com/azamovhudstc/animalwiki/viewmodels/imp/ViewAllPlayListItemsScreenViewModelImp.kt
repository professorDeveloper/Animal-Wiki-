package com.azamovhudstc.animalwiki.viewmodels.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.animalwiki.data.remote.response.playListResponse.PlayListResponse
import com.azamovhudstc.animalwiki.usecases.ViewAllPlayListsScreenUseCase
import com.azamovhudstc.animalwiki.viewmodels.ViewAllPlayListItemsScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class ViewAllPlayListItemsScreenViewModelImp @Inject constructor(private val viewAllPlayListsScreenUseCase: ViewAllPlayListsScreenUseCase) :
    ViewAllPlayListItemsScreenViewModel, ViewModel() {
    override var progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override var getAllPlayListItemsLiveData: MutableLiveData<PlayListResponse> = MutableLiveData()
    override var backLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun getAllViewAllPlayListItems(playListId: String) {
        progressLiveData.value = true
        viewAllPlayListsScreenUseCase.getAllPlayLists(playListId).onEach {
            getAllPlayListItemsLiveData.value = it
            progressLiveData.value = false
        }
            .launchIn(viewModelScope)
    }

    override fun backLiveData() {
        backLiveData.value = Unit
    }
}