package com.example.firebaseproject.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "AppDataBase")
class DataModel : Serializable {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("Id")
    var Id: Int = 0

    @SerializedName("Name")
    var name: String = ""

    constructor(Id: Int, name: String) {
        this.Id = Id
        this.name = name
    }
}
