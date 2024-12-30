package com.endava.multikotlinapp.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<KmmDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), DATABASE_NAME)
        return Room.databaseBuilder<KmmDatabase>(
            name = dbFile.absolutePath,
        )
    }
}