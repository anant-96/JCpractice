package me.amitshekhar.newsapp.data.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.amitshekhar.newsapp.data.repository.OfflineArticleRepository
import me.amitshekhar.newsapp.utils.AppConstant

@HiltWorker
open class NewsWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val offlineArticleRepository: OfflineArticleRepository,
) : Worker(context, params) {
    override fun doWork(): Result {
        return try {
            Log.d("NewsWorker", "NewsWorker:  worker called")
            CoroutineScope(Dispatchers.IO).launch {
                offlineArticleRepository.fetchAndStoreArticlesInDB(AppConstant.COUNTRY)
            }
            Result.success()
        } catch (e: Exception) {
            println("Exception : ${e.message}")
            Result.retry()
        }
    }
}
