package com.azamovhudstc.animalwiki.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.alterac.blurkit.BlurKit

@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        BlurKit.init(this)
        instance = this
    }

}
