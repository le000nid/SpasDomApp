package com.example.spasdomworkerapp.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.spasdomworkerapp.di.ApplicationScope
import com.example.spasdomworkerapp.network.NetworkOrderItem
import com.example.spasdomworkerapp.network.NetworkOrdersContainer
import com.example.spasdomworkerapp.network.asDatabaseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [DatabaseOrderItem::class], version = 1)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun cacheDao(): CacheDao

    class Callback @Inject constructor(
        private val database: Provider<CacheDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            Log.i("Callback", "callback called")

            val cacheDao = database.get().cacheDao()

            applicationScope.launch {
                val ordersInit: List<NetworkOrderItem> = listOf(
                    NetworkOrderItem("27-11-2021", "Улица улная", "19:00 - 20:00", "Нада", true, false, 5),
                    NetworkOrderItem("28-11-2021", "Улица уная", "11:00 - 12:00", "Нада", true,false, 1),
                    NetworkOrderItem("28-11-2021", "Улица уличная", "13:00 - 14:00", "Нада", false,false, 2),
                    NetworkOrderItem("28-11-2021", "Улица улиная", "15:00 - 16:00", "Нада", false,false, 3),
                    NetworkOrderItem("28-11-2021", "Морской проспект 9", "17:00 - 18:00", "Бачок потик", true,false, 4),
                    NetworkOrderItem("29-11-2021", "Не морской не проспект", "3:30 - 4:20", "О боже", false,false, 6),
                    NetworkOrderItem("05-12-2021", "5 декабря :)", "3:30 - 4:20", "Работает", false,false, 7),
                    NetworkOrderItem("06-12-2021", "6 декабря :)", "3:30 - 4:20", "Работает", false,false, 8),
                )
                val orders = NetworkOrdersContainer(ordersInit)
                cacheDao.insertAllOrders(*orders.asDatabaseModel())
            }
        }
    }
}