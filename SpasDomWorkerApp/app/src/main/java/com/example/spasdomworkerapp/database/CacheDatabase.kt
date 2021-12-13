package com.example.spasdomworkerapp.database

import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.spasdomworkerapp.di.ApplicationScope
import com.example.spasdomworkerapp.models.Order
import com.example.spasdomworkerapp.models.PlannedOrder
import com.example.spasdomworkerapp.network.asDatabaseModel
import com.example.spasdomworkerapp.responses.OrderListResponse
import com.example.spasdomworkerapp.responses.asCacheModel
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
                val ordersInit: List<PlannedOrder> = listOf(
                    PlannedOrder(5, "Пр. Ленина 24а", "2021-12-09T14:32:46.848Z", 0),
                    PlannedOrder(6, "Станиславского 4", "2021-12-09T14:32:46.848Z", 0),
                    PlannedOrder(7, "Выставочная 17", "2021-12-09T14:32:46.848Z", 0),
                    PlannedOrder(8, "Никитина 12", "2021-12-09T14:32:46.848Z", 0),
                    PlannedOrder(9, "Морской проспект 9", "2021-12-09T14:32:46.848Z", 0),
                    PlannedOrder(10, "Большивисткая 1", "2021-12-09T14:32:46.848Z", 0),
                    PlannedOrder(11, "Пр. Ленина 24а", "2021-12-09T14:32:46.848Z", 0),
                    PlannedOrder(12, "Станиславского 4", "2021-12-09T14:32:46.848Z", 0),
                )
//                val orders = OrderListResponse(ordersInit)
//                cacheDao.insertAllOrders(*orders.asCacheModel())
            }
        }
    }
}