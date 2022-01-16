package com.example.ghadeer_ss.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//create database...
@Database(entities = [UN::class], version = 1, exportSchema = false)
abstract class UNDatabase : RoomDatabase() {

    abstract fun UNDao(): UNDao;

    companion object {
        var instance: UNDatabase? = null;

        fun getInstance(ctx: Context): UNDatabase {
            if (instance != null) {
                return instance as UNDatabase;
            }
            instance = Room.databaseBuilder(ctx, UNDatabase::class.java, "UniversityDB.db")
                .run { allowMainThreadQueries() }.build();
            return instance as UNDatabase;
        }
    }

}