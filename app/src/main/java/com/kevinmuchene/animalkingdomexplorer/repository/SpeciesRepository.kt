package com.kevinmuchene.animalkingdomexplorer.repository

import com.kevinmuchene.animalkingdomexplorer.database.SpeciesDatabase
import com.kevinmuchene.animalkingdomexplorer.model.Species

class SpeciesRepository(private val db: SpeciesDatabase) {

    suspend fun addSpecies(species: Species) = db.getSpeciesDao().addSpecies(species)

    fun getAllSpecies() = db.getSpeciesDao().getAllSpecies()
}