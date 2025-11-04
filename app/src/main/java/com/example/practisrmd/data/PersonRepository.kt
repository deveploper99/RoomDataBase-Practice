package com.example.practisrmd.data

class PersonRepository(private val dao: PersonDao) {
    val allPersons  = dao.getAllPerson()
    suspend fun insert(person: Person) = dao.insert(person)
    suspend fun update (person: Person)= dao.update(person)
    suspend fun upsert(person: Person)=dao.upsert(person)
    suspend fun delete (person: Person) = dao.delete(person)
}