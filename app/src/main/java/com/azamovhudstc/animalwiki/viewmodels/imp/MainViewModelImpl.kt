package com.azamovhudstc.animalwiki.viewmodels.imp

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azamovhudstc.animalwiki.data.local.sharedpref.AppReference
import com.azamovhudstc.animalwiki.utils.enums.ScreenEnum
import com.azamovhudstc.animalwiki.viewmodels.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    @ApplicationContext  applicationContext: Context
) : ViewModel(), MainViewModel {
    override val loginScreenLiveData = MutableLiveData<Unit>()
    override val homeScreenLiveData = MutableLiveData<Unit>()

    init {
        val appReference: AppReference= AppReference(applicationContext)
        when (appReference.currentScreen) {
            ScreenEnum.LOGIN -> loginScreenLiveData.value = Unit
            ScreenEnum.HOME -> homeScreenLiveData.value = Unit
        }
    }
}
