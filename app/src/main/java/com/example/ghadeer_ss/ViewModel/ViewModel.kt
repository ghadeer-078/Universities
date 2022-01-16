package com.example.ghadeer_ss.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.ghadeer_ss.Database.UN
import com.example.ghadeer_ss.Database.UNDatabase
import com.example.ghadeer_ss.Database.UniversityRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UniversityRepository
    private val university: LiveData<List<UN>>

    init {
        val universityDao = UNDatabase.getInstance(application).UNDao()
        repository = UniversityRepository(universityDao)
        university = repository.getUniversity
    }


    fun getUniversityDB(): LiveData<List<UN>> {
        return university
    }

    fun addUniversityDB(universityName: String, country: String, note: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addUniversity(UN(0, universityName, country, note))
        }
    }

    fun deleteUniversityDB(ID: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteUniversity(ID)
        }
    }

    fun updateUniversityDB(newNote: String, id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateNote(newNote, id)
        }
    }
}