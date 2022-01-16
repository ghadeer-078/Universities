package com.example.ghadeer_ss.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//first create model database...
//table name
@Entity(tableName = "UN")
data class UN(
    // table contents
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val universityName: String,
    val country: String,
    val note: String = ""
)