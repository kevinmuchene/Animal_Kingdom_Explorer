package com.kevinmuchene.animalkingdomexplorer.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kevinmuchene.animalkingdomexplorer.model.Species

@Dao
interface SpeciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSpecies(species: Species)

    @Query("SELECT * FROM species")
    fun getAllSpecies(): LiveData<List<Species>>
}