package com.azamovhudstc.animalwiki.viewmodels

import androidx.lifecycle.MutableLiveData

interface GetStartedScreenViewModel {
    val openHomeScreenLiveData:MutableLiveData<Unit>
    fun openHomeScreen()
}