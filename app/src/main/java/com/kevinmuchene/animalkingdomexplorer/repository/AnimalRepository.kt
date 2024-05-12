package com.kevinmuchene.animalkingdomexplorer.repository

import com.kevinmuchene.animalkingdomexplorer.database.AnimalDatabase
import com.kevinmuchene.animalkingdomexplorer.model.Animal

class AnimalRepository(private val db: AnimalDatabase) {

    suspend fun addAnimal(animal: Animal) = db.getAnimalDao().insertAnimal(animal)

    fun getAllAnimals() = db.getAnimalDao().getAllAnimals()
}