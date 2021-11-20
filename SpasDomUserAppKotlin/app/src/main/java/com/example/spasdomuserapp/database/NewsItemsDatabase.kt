package com.example.spasdomuserapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [DatabaseNewsItem::class], version = 1)
abstract class NewsItemsDatabase : RoomDatabase() {
    abstract val newsItemsDao: NewsItemsDao
}

private lateinit var INSTANCE: NewsItemsDatabase

/**
 * Inside getDatabase(), use ::INSTANCE.isInitialized to check if the variable has been initialized.
 *  If it hasn't, then initialize it. Make sure your code is synchronized so it’s thread safe:
 */
fun getDatabase(context: Context): NewsItemsDatabase {
    synchronized(NewsItemsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                NewsItemsDatabase::class.java,
                "newsItems").build()
        }
    }
    return INSTANCE
}