package com.endava.multikotlinapp.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.endava.multikotlinapp.data.local.entities.ReadLaterItem

@Dao
interface ReadLaterDao {

    @Query("SELECT * FROM ReadLaterItem")
    suspend fun getAll(): List<ReadLaterItem>

    @Upsert
    suspend fun upsert(readLaterItem: ReadLaterItem)

    @Delete
    suspend fun delete(readLaterItem: ReadLaterItem)
}