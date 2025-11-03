package com.example.practisrmd.data

import androidx.lifecycle.LiveData
import androidx.room.*

// ✅ DAO Interface
@Dao
interface PersonDao {

    // ✅ Insert -> with replace strategy
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: Person)

    // ✅ Update
    @Update
    suspend fun update(person: Person)

    // ✅ Upsert (If you're using Room version 2.6.0+)
    @Upsert
    suspend fun upsert(person: Person)

    // ✅ Delete
    @Delete
    suspend fun delete(person: Person)

    // ✅ Get all persons ordered by ID (descending)
    @Query("SELECT * FROM person_table ORDER BY id DESC")
    fun getAllPerson(): LiveData<List<Person>>
}
