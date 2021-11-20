package com.example.spasdomuserapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.spasdomuserapp.database.getDatabase
import com.example.spasdomuserapp.ui.home.NewsItemsRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    /**
     * A coroutine-friendly method to do your work.
     */
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = NewsItemsRepository(database)
        return try {
            repository.refreshNewsItems()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}
