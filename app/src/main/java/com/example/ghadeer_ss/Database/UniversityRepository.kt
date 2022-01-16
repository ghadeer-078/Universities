package com.example.ghadeer_ss.Database

import androidx.lifecycle.LiveData

class UniversityRepository(private val universityDao: UNDao) {

    val getUniversity: LiveData<List<UN>> = universityDao.getUniversity()

    suspend fun addUniversity(item: UN) {
        universityDao.addUniversity(item)
    }

    suspend fun deleteUniversity(universityID: Int) {
        universityDao.deleteUniversity(universityID)
    }

    suspend fun updateNote(updatedNote: String, id: Int) {
        universityDao.updateNote(updatedNote, id)
    }
}