package com.example.spasdomuserapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.spasdomuserapp.di.ApplicationScope
import com.example.spasdomuserapp.models.*
import com.example.spasdomuserapp.responses.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


@Database(entities = [CacheNewsItem::class, CacheAlert::class, CachePlannedOrder::class, CacheMarketOrder::class], version = 1)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun cacheDao(): CacheDao

    class Callback @Inject constructor(
        private val database: Provider<CacheDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val cacheDao = database.get().cacheDao()
            
            /*applicationScope.launch {
                val alertsInit: List<NetworkAlert> = listOf(
                    NetworkAlert(1, "20 Октбяра с 3 до 8", "Отключение горячей воды", "Описание1"),
                    NetworkAlert(2, "21 Октбяра с 3 до 8", "Отключение Холодной воды", "Описание2"),
                )
                cacheDao.insertAllAlerts(*alertsInit.asCacheAlertsModel())


                val alertsInit: List<Alert> = listOf(
                    Alert("20 Октбяра с 3 до 8", "Отключение горячей воды","Описание1"),
                    Alert("21 Октбяра с 3 до 8", "Отключение Холодной воды","Описание2"),
                )
                val alerts = AlertsResponse(alertsInit)
                cacheDao.insertAllAlerts(*alerts.asCacheAlertModel())

                // 0 - active 1 - finished
                val itemsInit: List<Order> = listOf(
                    Order(1,"Проверка счетчиков","24.01.21","14:00-15:00",0,"",0,"","Петр Васильев", 4, "No info"),
                    Order(2,"Проверка воды","25.01.21","17:00-18:00",0,"",1,"","Александр Васильев", 2, "No info"),
                    Order(3,"Проверка крана","27.01.21","12:00-13:00",5,"Все прекрасно",1,"","Петр Васильев", 4, "No info"),
                )
                val items = OrderListResponse(itemsInit)
                cacheDao.insertAllPlannedOrders(*items.asCachePlannedModel())


                // 0 - active 1 - finished
                val itemsMarketInit: List<Order> = listOf(
                    Order(1,"Маркет счетчики","24.01.21","14:00-15:00",0,"",0,"","Петр Васильев", 4, "No info"),
                    Order(2,"Маркет воды","25.01.21","17:00-18:00",0,"",1,"","Александр Васильев", 2, "No info"),
                    Order(3,"Маркет крана","27.01.21","12:00-13:00",5,"Все прекрасно",1,"","Петр Васильев", 4, "No info"),
                )
                val itemsMarket = OrderListResponse(itemsMarketInit)
                cacheDao.insertAllMarketOrders(*itemsMarket.asCacheMarketModel())
            }*/
        }
    }
}
