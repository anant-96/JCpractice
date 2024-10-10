package me.amitshekhar.newsapp

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import me.amitshekhar.newsapp.data.worker.NewsWorker
import me.amitshekhar.newsapp.utils.AppConstant
import me.amitshekhar.newsapp.utils.TimeUtil
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class NewsApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var workManager: WorkManager


    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        initWorkManager()
    }
//    private fun setUpWorker() {
//        // Create a retry policy with exponential backoff
//        val retryPolicy = BackoffPolicy.EXPONENTIAL
//        val retryInterval = 15L // Retry every 5 minutes
//        val retryIntervalTimeUnit = TimeUnit.MINUTES
//        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
//        val workRequest = PeriodicWorkRequest.Builder(NewsWorker::class.java, 1, TimeUnit.DAYS)
//            .setBackoffCriteria(retryPolicy, retryInterval, retryIntervalTimeUnit)
//            .setConstraints(constraint)
//            .build()
//        WorkManager.getInstance(this).enqueue(workRequest)
//    }

    private fun initWorkManager() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest = PeriodicWorkRequest.Builder(
            NewsWorker::class.java,
            24,
            TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .setInitialDelay(TimeUtil.getInitialDelay(), TimeUnit.MILLISECONDS)
            .addTag(AppConstant.UNIQUE_WORK_NAME)
            .build()

        workManager.enqueueUniquePeriodicWork(
            AppConstant.UNIQUE_WORK_NAME,
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            workRequest
        )
    }
}
