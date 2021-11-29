package com.example.spasdomuserapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.selects.select

@Dao
interface CacheDao {
    @Query("select * from databasenewsitem")
    fun getNewsItems(): LiveData<List<DatabaseNewsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNews(vararg videos: DatabaseNewsItem)


    @Query("select * from databasealert")
    fun getAlerts(): LiveData<List<DataBaseAlert>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAlerts(vararg alerts: DataBaseAlert)


    @Query("select * from cacheplannedorder where isFinished = 0")
    fun getActivePlannedOrders(): LiveData<List<CachePlannedOrder>>

    @Query("select * from cacheplannedorder where isFinished = 1")
    fun getHistoryPlannedOrders(): LiveData<List<CachePlannedOrder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPlannedOrders(vararg alerts: CachePlannedOrder)

    @Update
    suspend fun updatePlannedOrder(order: CachePlannedOrder)
}