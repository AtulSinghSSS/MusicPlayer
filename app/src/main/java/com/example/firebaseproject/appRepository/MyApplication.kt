package com.example.firebaseproject.appRepository

import android.app.Activity
import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.firebaseproject.db.AppDatabase

class MyApplication : Application(), LifecycleObserver {
    lateinit var appDatabase: AppDatabase
    lateinit var mInstance: MyApplication
    lateinit var activity: Activity

    @Synchronized
    fun getInstance(): MyApplication {
        return mInstance
    }
    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
      //  apiService = RetrofitHelper.INSTANCE.getInstance(applicationContext)
        appDatabase = AppDatabase.getDatabase(applicationContext)
        mInstance = this
    }
}
