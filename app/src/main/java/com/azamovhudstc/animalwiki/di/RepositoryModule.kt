package com.azamovhudstc.animalwiki.di

import com.azamovhudstc.animalwiki.repo.*
import com.azamovhudstc.animalwiki.repo.imp.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun getPostsRepository(impl: PostsRepositoryImp): PostsRepository
    @Binds
    fun  getNetworkRepository(impl:NetworkRepositoryImp):NetworkRepository
    @Binds
    fun getVideosRepository(impl: VideosRepositoryImp): VideosRepository

    @Binds
    fun getVideoInfoRepository(impl: VideoInfoRepositoryImp): VideoInfoRepository

    @Binds
    fun getImagesInfoRepository(impl: ImagesRepositoryImp): ImagesRepository


}