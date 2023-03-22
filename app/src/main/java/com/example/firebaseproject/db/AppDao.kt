package com.example.firebaseproject.db

import androidx.room.Dao
import androidx.room.Insert


@Dao
interface AppDao {
    @Insert
     fun insertCustData(model: DataModel?)

}
