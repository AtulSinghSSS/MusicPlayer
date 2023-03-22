package com.example.firebaseproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataModel::class,],version =1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun AppDaoAccess(): AppDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private var DB_NAME = "Music_player"
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,
                        AppDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
