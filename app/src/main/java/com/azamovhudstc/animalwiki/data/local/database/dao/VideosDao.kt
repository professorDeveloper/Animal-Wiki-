package com.azamovhudstc.animalwiki.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.azamovhudstc.animalwiki.data.local.database.entites.GeneralEntities

@Dao
interface VideosDao {


    @Query("SELECT * FROM GeneralEntities")
    suspend fun getAllBooks(): List<GeneralEntities>
    @Insert
    suspend fun addGeneric(generalEntities: GeneralEntities)

    @Query("DELETE FROM GeneralEntities")
    suspend fun clear()


}