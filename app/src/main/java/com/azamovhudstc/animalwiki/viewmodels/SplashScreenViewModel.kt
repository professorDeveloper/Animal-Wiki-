package com.azamovhudstc.animalwiki.viewmodels

import androidx.lifecycle.MutableLiveData

interface SplashScreenViewModel {
    val openGetStartedScreenLiveData:MutableLiveData<Unit>
    val openHomeScreenLiveData:MutableLiveData<Unit>
    fun openGetStartedScreen()
    fun openHomeScreen()
}