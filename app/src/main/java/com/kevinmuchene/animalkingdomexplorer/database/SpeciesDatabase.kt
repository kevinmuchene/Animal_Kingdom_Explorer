package com.kevinmuchene.animalkingdomexplorer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kevinmuchene.animalkingdomexplorer.model.Species

@Database(entities = [Species::class], version = 1)
abstract class SpeciesDatabase: RoomDatabase() {

    abstract fun getSpeciesDao(): SpeciesDao

    companion object {

        @Volatile
        private var instance: SpeciesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK) {
            createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                SpeciesDatabase::class.java,
                "species_db"
            ).build()
    }
}