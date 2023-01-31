package com.azamovhudstc.animalwiki.viewmodels.imp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azamovhudstc.animalwiki.viewmodels.SplashScreenViewModel

class SplashScreenViewModelImp : SplashScreenViewModel, ViewModel() {
    override val openGetStartedScreenLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val openHomeScreenLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun openGetStartedScreen() {
        openGetStartedScreenLiveData.value=Unit
    }

    override fun openHomeScreen() {
        openHomeScreenLiveData.value=Unit
    }
}