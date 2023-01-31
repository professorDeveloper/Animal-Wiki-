package com.azamovhudstc.animalwiki.viewmodels

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.animalwiki.data.local.models.WikiPostData
import com.azamovhudstc.animalwiki.utils.enums.PostTypes

interface PostItemScreenViewModel {
    val onClickLiveData:MutableLiveData<WikiPostData>
    val postItemsLiveData: MutableLiveData<ArrayList<WikiPostData>>
    fun getPostItems(postTypes: PostTypes)
    fun onClick(wikiPostData: WikiPostData)
}