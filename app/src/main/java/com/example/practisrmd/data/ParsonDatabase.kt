package com.example.practisrmd.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)

abstract class ParsonDatabase: RoomDatabase() {

    abstract fun personDao(): PersonDao


    companion object{
        @Volatile
        private var INSTANCE: ParsonDatabase? = null



        fun getDatabase(context: Context):ParsonDatabase{
            return INSTANCE ?: synchronized((this)){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ParsonDatabase::class.java,
                    "person_table"
                ).build()
                INSTANCE = instance
                instance

            }

        }

    }
}