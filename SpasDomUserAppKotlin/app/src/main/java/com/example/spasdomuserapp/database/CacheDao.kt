package com.example.spasdomuserapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.selects.select

@Dao
interface CacheDao {

    // ---------- News ----------

    @Query("select * from databasenewsitem")
    fun getNewsItems(): LiveData<List<DatabaseNewsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNews(vararg news: DatabaseNewsItem)


    // ---------- Alerts ----------

    @Query("select * from databasealert")
    fun getAlerts(): LiveData<List<DataBaseAlert>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAlerts(vararg alerts: DataBaseAlert)


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