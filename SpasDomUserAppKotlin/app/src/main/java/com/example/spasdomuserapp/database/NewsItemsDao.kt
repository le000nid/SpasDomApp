package com.example.spasdomuserapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsItemsDao {
    @Query("select * from databasenewsitem")
    fun getNewsItems(): LiveData<List<DatabaseNewsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DatabaseNewsItem)
}