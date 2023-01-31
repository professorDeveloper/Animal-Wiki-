package com.azamovhudstc.animalwiki.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

fun <T> MediatorLiveData<*>.addDisposable(liveData: LiveData<T>, observer: Observer<T>){
    addSource(liveData) {
        observer.onChanged(it)
        removeSource(liveData)
    }
}