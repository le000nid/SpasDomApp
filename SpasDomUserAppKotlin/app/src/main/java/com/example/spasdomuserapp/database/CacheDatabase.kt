package com.example.spasdomuserapp.database

import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.spasdomuserapp.di.ApplicationScope
import com.example.spasdomuserapp.models.PlannedOrder
import com.example.spasdomuserapp.network.*
import com.example.spasdomuserapp.responses.PlannedListResponse
import com.example.spasdomuserapp.responses.asCacheModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


@Database(entities = [DatabaseNewsItem::class, DataBaseAlert::class, CachePlannedOrder::class], version = 1)
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
                val newsInit: List<NetworkNewsItem> = listOf(
                    NetworkNewsItem("Украли дверь", "Вечером 22.03 вынесли дверь. Соседе вызвали полицию!","1","22.03.21", "", ""),
                    NetworkNewsItem("Украли соседа", "Вечером 24.03 украли соседа. Люди в страхе!","2","24.03.21", "",""),
                    NetworkNewsItem("Украли жену соседа", "Вечером 25.03 украли жену соседа. Люди продают квартиры!","3","25.03.21", "","")
                )
                val threeNews = NetworkNewsContainer(newsInit)
                cacheDao.insertAllNews(*threeNews.asDatabaseModel())


                val alertsInit: List<NetworkAlerts> = listOf(
                    NetworkAlerts("20 Октбяра с 3 до 8", "Отключение горячей воды","Описание1"),
                    NetworkAlerts("21 Октбяра с 3 до 8", "Отключение Холодной воды","Описание2"),
                )
                val alerts = NetworkAlertsContainer(alertsInit)
                cacheDao.insertAllAlerts(*alerts.asDatabaseAlertModel())

                // 0 - active 1 - finished
                val itemsInit: List<PlannedOrder> = listOf(
                    PlannedOrder(1,"Проверка счетчиков","24.01.21","14:00-15:00",0,"",0,"","Петр Васильев", 4, "No info"),
                    PlannedOrder(2,"Проверка воды","25.01.21","17:00-18:00",0,"",1,"","Александр Васильев", 2, "No info"),
                    PlannedOrder(3,"Проверка крана","27.01.21","12:00-13:00",5,"Все прекрасно",1,"","Петр Васильев", 4, "No info"),
                )
                val items = PlannedListResponse(itemsInit)
                cacheDao.insertAllPlannedOrders(*items.asCacheModel())
            }
        }
    }
}
