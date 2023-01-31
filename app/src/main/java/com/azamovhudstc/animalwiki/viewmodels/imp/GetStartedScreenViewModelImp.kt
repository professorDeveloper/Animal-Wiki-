package com.azamovhudstc.animalwiki.viewmodels.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azamovhudstc.animalwiki.viewmodels.GetStartedScreenViewModel

class GetStartedScreenViewModelImp: GetStartedScreenViewModel,ViewModel() {
    override val openHomeScreenLiveData: MutableLiveData<Unit> =MutableLiveData()
    override fun openHomeScreen() {
        openHomeScreenLiveData.value=Unit
    }
}