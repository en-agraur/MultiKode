package com.endava.multikotlinapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReadLaterItem(
    val author: String?,
    val title: String,
    val description: String?,
    val thumbnail: String?,
    @PrimaryKey(autoGenerate = false) val url: String,
    val publishedAt: String?,
    val source: String?
)
