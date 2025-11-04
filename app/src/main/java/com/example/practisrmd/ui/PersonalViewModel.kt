package com.example.practisrmd.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.practisrmd.data.ParsonDatabase
import com.example.practisrmd.data.Person
import com.example.practisrmd.data.PersonRepository
import kotlinx.coroutines.launch

class PersonalViewModel(application: Application): AndroidViewModel(application) {

    private val repository : PersonRepository
    val allPerson: LiveData<List<Person>>

    init {
        val dao = ParsonDatabase.getDatabase(application).personDao()
        repository = PersonRepository(dao)
        allPerson = repository.allPersons
    }


    fun insert(person: Person) = viewModelScope.launch {
        repository.insert(person)
    }

    fun update (person: Person) = viewModelScope.launch {
        repository.update(person)
    }

    fun upsert (person: Person) = viewModelScope.launch {
        repository.upsert(person)
    }

    fun delete (person: Person) = viewModelScope.launch {
        repository.delete(person)
    }
}