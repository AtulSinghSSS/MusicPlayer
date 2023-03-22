package com.example.firebaseproject.appRepository

import android.content.Context
import com.example.firebaseproject.db.DataModel

class AppRepository(applicationContext: Context) {
    //var apiService = (applicationContext as MyApplication).apiService
    var appdatabase = (applicationContext as MyApplication).appDatabase

    fun insertData(model: DataModel?) = appdatabase.AppDaoAccess()?.insertCustData(model)
}
