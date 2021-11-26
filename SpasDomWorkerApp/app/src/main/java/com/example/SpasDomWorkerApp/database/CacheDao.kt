package com.example.spasdomworkerapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CacheDao {
    @Query("select * from databaseorderitem")
    fun getOrderItems(): LiveData<List<DatabaseOrderItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg orders: DatabaseOrderItem)
}