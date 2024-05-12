package com.kevinmuchene.animalkingdomexplorer.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kevinmuchene.animalkingdomexplorer.model.Animal

@Dao
interface AnimalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimal(animal: Animal)
    @Query("SELECT * FROM animals")
    fun getAllAnimals(): LiveData<List<Animal>>
}