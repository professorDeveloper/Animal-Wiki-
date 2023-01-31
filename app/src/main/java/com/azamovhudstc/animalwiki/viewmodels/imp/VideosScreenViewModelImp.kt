package com.azamovhudstc.animalwiki.viewmodels.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.animalwiki.data.model.GeneralModel
import com.azamovhudstc.animalwiki.usecases.VideosScreenUseCase
import com.azamovhudstc.animalwiki.viewmodels.VideosScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideosScreenViewModelImp @Inject constructor(private val homeScreenUseCase: VideosScreenUseCase) :
    VideosScreenViewModel, ViewModel() {
    override val quranPlayListsLiveData: MutableLiveData<ArrayList<GeneralModel>> =
        MutableLiveData()
    override val progressData: MutableLiveData<Boolean> = MutableLiveData()

    override val viewAllPlayList: MutableLiveData<GeneralModel> = MutableLiveData()
    override val openNavigationLiveData: MutableLiveData<Unit> = MutableLiveData()
    override fun openNavigation() {
        TODO("Not yet implemented")
    }


    override fun getAllGeneralData() {
        progressData.value=true
        homeScreenUseCase.getAllGeneralData().onEach {
            quranPlayListsLiveData.value = it
            progressData.value=false
        }
            .launchIn(viewModelScope)

    }


    override fun viewAllPlayList(playListId: GeneralModel) {
        viewAllPlayList.value = playListId
    }
}