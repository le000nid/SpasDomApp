package com.example.spasdomworkerapp.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.spasdomworkerapp.di.ApplicationScope
import com.example.spasdomworkerapp.models.Order
import com.example.spasdomworkerapp.network.NetworkOrderItem
import com.example.spasdomworkerapp.network.NetworkOrdersContainer
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
                val ordersInit: List<Order> = listOf(
                    Order("09-12-2021", "Пр. Ленина 24а", "10:00 - 11:00", "Течет кран", false, false, 1),
                    Order("09-12-2021", "Станиславского 4", "12:00 - 14:00", "Прорвало трубу", false,false, 2),
                    Order("09-12-2021", "Выставочная 17", "16:00 - 18:00", "Заменить кран", false,false, 3),
                    Order("09-12-2021", "Никитина 12", "19:00 - 20:00", "Засорилась канолизация", false,false, 4),
                    Order("10-12-2021", "Морской проспект 9", "17:00 - 18:00", "Почистить трубы", false,false, 5),
                    Order("10-12-2021", "Большивисткая 1", "20:00 - 21:00", "Проверка счетчиков", false,false, 6),
                    Order("08-12-2021", "Пр. Ленина 24а", "15:00 - 17:00", "Течет кран", false,false, 7),
                    Order("08-12-2021", "Станиславского 4", "18:00 - 19:00", "Прорвало трубу", false,false, 8),
                )
//                val orders = OrderListResponse(ordersInit)
//                cacheDao.insertAllOrders(*orders.asCacheModel())
            }
        }
    }
}