package com.azamovhudstc.animalwiki.di

import android.content.Context
import androidx.room.Room
import com.azamovhudstc.animalwiki.data.local.database.appDatabase.AppDatabase
import com.azamovhudstc.animalwiki.data.local.database.dao.VideosDao
import com.azamovhudstc.animalwiki.repo.PostsRepository
import com.azamovhudstc.animalwiki.repo.imp.PostsRepositoryImp
import dagger.Binds

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class VideosDatabaseModule {
    @[Provides Singleton]
    fun provideVideosDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "animalWiki.db")
            .build()

    }

    @Provides
    fun provideVideosDao(appDatabase: AppDatabase): VideosDao {
        return appDatabase.bookDao()
    }
}
