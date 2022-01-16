package com.example.ghadeer_ss.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UNDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUniversity(item: UN)

    @Query("SELECT * FROM UN ORDER BY id ASC")
    fun getUniversity(): LiveData<List<UN>>


    @Query("Delete from UN where ID=:universityID")
    suspend fun deleteUniversity(universityID: Int)

    @Query("UPDATE UN SET note=:updatedNote where ID=:uniID")
    suspend fun updateNote(updatedNote: String, uniID:Int)
}