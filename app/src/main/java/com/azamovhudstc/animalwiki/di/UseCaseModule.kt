package com.azamovhudstc.animalwiki.di

import com.azamovhudstc.animalwiki.usecases.*
import com.azamovhudstc.animalwiki.usecases.imp.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindPostsScreenUseCase(impl: PostsScreenUseCaseImp): PostsScreenUseCase

    @Binds
    fun bindPostItemsScreenUseCase(impl: PostItemScreenUseCaseImp): PostItemScreenUseCase

    @Binds
    fun bindVideosScreenUseCase(impl: VideosScreenUseCaseImp): VideosScreenUseCase

    @Binds
    fun bindViewAllPlaylistItemsUseCase(impl: ViewAllPlayListsScreenUseCaseImp): ViewAllPlayListsScreenUseCase

    @Binds
    fun bindVideoInfoScreenUseCase(impl: VideoInfoScreenUseCaseImp): VideoInfoScreenUseCase
    @Binds
    fun bindImageScreenUseCase(impl: ImagePageScreenUseCaseImp): ImagePageScreenUseCase
}