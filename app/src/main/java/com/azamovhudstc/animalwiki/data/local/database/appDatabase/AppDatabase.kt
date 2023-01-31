package com.azamovhudstc.animalwiki.data.local.database.appDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.azamovhudstc.animalwiki.data.local.database.dao.VideosDao
import com.azamovhudstc.animalwiki.data.local.database.entites.GeneralEntities
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@Database(entities = [GeneralEntities::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): VideosDao

}