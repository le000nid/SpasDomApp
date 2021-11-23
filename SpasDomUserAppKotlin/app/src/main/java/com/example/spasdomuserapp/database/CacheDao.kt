package com.example.spasdomuserapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.selects.select

@Dao
interface CacheDao {
    @Query("select * from databasenewsitem")
    fun getNewsItems(): LiveData<List<DatabaseNewsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DatabaseNewsItem)


    @Query("select * from databasealert")
    fun getAlerts(): LiveData<List<DataBaseAlert>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAlerts(vararg alerts: DataBaseAlert)


    @Query("select * from cacheactiveplannedorder")
    fun getActivePlannedOrders(): LiveData<List<CacheActivePlannedOrder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllActivePlannedOrders(vararg alerts: CacheActivePlannedOrder)
}