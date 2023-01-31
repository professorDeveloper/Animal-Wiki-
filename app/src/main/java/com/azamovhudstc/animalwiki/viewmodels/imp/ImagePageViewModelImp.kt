package com.azamovhudstc.animalwiki.viewmodels.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImagesResponse
import com.azamovhudstc.animalwiki.usecases.ImagePageScreenUseCase
import com.azamovhudstc.animalwiki.viewmodels.ImagePageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ImagePageViewModelImp @Inject constructor(private val imagePageScreenUseCase: ImagePageScreenUseCase) :ViewModel() {
//    override val imagesLiveData: MutableLiveData<ImagesResponse> = MutableLiveData()
    val progressLiveData=MutableLiveData<Boolean>()
    fun searchPhoto(query:String){
        progressLiveData.value=true
    currentQuery.value = query
    }
    private val currentQuery = MutableLiveData(DEFAULT_QUERY)
    companion object {
        private const val DEFAULT_QUERY = "animals"
    }
    val  photos = currentQuery.switchMap { queryString -> imagePageScreenUseCase.getQueryByImage(queryString,"").cachedIn(viewModelScope)
    }

}