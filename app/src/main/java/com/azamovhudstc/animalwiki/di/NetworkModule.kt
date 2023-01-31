package com.azamovhudstc.animalwiki.di

import android.content.Context
import com.azamovhudstc.animalwiki.app.App
import com.azamovhudstc.animalwiki.data.remote.api.UnsplashApi
import com.azamovhudstc.animalwiki.data.remote.api.YoutubeApi
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @[Provides Singleton]
    fun getOkHTTPClient(@ApplicationContext context: Context): OkHttpClient = OkHttpClient.Builder()
        .build()


    @[Provides Singleton Named("YoutubeApi")]
    fun getYoutubeRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/youtube/v3/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @[Provides Singleton Named("UnsplashApi")]
    fun getUnsplashRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.unsplash.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun getYoutubeApi(@Named("YoutubeApi") retrofit: Retrofit): YoutubeApi =
        retrofit.create(YoutubeApi::class.java)

    @Provides
    fun getUnsplashApi(@Named("UnsplashApi") retrofit: Retrofit): UnsplashApi =
        retrofit.create(UnsplashApi::class.java)


}