package com.endava.multikotlinapp.data.local

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<KmmDatabase>
}