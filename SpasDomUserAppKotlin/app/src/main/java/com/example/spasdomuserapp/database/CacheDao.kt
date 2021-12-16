package com.example.spasdomuserapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CacheDao {

    // ---------- News ----------

    @Query("select * from cachenewsitem")
    fun getNewsItems(): LiveData<List<CacheNewsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNews(vararg news: CacheNewsItem)


    // ---------- Alerts ----------

    @Query("select * from cachealert")
    fun getAlerts(): LiveData<List<CacheAlert>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAlerts(vararg alerts: CacheAlert)


    // ---------- Planned orders ----------

    @Query("select * from cacheplannedorder where status = :status")
    fun getPlannedOrders(status: Int): LiveData<List<CachePlannedOrder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPlannedOrders(vararg orders: CachePlannedOrder)

    @Update
    suspend fun updatePlannedOrder(order: CachePlannedOrder)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlannedOrder(order: CachePlannedOrder)


    // ---------- Market orders ----------

    @Query("select * from cachemarketorder where status = :status")
    fun getMarketOrders(status: Int): LiveData<List<CacheMarketOrder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMarketOrders(vararg orders: CacheMarketOrder)

    @Update
    suspend fun updateMarketOrder(order: CacheMarketOrder)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMarketOrder(order: CacheMarketOrder)
}