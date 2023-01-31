package com.azamovhudstc.animalwiki.viewmodels

import androidx.lifecycle.MutableLiveData
import com.azamovhudstc.animalwiki.data.local.models.WikiPost
import com.azamovhudstc.animalwiki.ui.screen.pages.PostsPage

interface PostsScreenViewModel {
    val postClickLiveData:MutableLiveData<WikiPost>
    val loadAllPostsLiveData:MutableLiveData<ArrayList<WikiPost>>
    fun loadAllPosts()
    fun onClick(postsPage: WikiPost)
}