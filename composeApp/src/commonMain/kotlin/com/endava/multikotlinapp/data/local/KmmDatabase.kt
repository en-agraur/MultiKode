package com.endava.multikotlinapp.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.endava.multikotlinapp.data.local.daos.ReadLaterDao
import com.endava.multikotlinapp.data.local.entities.ReadLaterItem

const val DATABASE_NAME = "read_later.db"

@Database(
    entities = [ReadLaterItem::class],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class KmmDatabase : RoomDatabase() {

    abstract fun getReadLaterDao(): ReadLaterDao

}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<KmmDatabase> {
    override fun initialize(): KmmDatabase
}