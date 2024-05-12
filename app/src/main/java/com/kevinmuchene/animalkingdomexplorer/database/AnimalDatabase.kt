package com.kevinmuchene.animalkingdomexplorer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kevinmuchene.animalkingdomexplorer.model.Animal


@Database(entities = [Animal::class], version = 1)
abstract class AnimalDatabase: RoomDatabase() {

    abstract fun getAnimalDao(): AnimalDao

    companion object {

        @Volatile
        private var instance:AnimalDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK) {
            instance?:
            createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AnimalDatabase::class.java,
                "animal_db"
            ).build()
    }
}