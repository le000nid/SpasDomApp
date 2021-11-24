package com.example.spasdomuserapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [DatabaseNewsItem::class, DataBaseAlert::class, CachePlannedOrder::class], version = 1)
abstract class CacheDatabase : RoomDatabase() {
    abstract val dao: CacheDao
}

private lateinit var INSTANCE: CacheDatabase

/**
 * Inside getDatabase(), use ::INSTANCE.isInitialized to check if the variable has been initialized.
 *  If it hasn't, then initialize it. Make sure your code is synchronized so it’s thread safe:
 */
fun getDatabase(context: Context): CacheDatabase {
    synchronized(CacheDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                CacheDatabase::class.java,
                "cache").build()
        }
    }
    return INSTANCE
}