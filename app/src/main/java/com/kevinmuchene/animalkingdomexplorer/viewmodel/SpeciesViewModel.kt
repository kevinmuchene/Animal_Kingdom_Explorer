package com.kevinmuchene.animalkingdomexplorer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kevinmuchene.animalkingdomexplorer.model.Species
import com.kevinmuchene.animalkingdomexplorer.repository.SpeciesRepository
import kotlinx.coroutines.launch

class SpeciesViewModel(app: Application, private val speciesRepository: SpeciesRepository): AndroidViewModel(app) {

    fun addSpecies(species: Species) {
        viewModelScope.launch {
            speciesRepository.addSpecies(species)
        }
    }

    fun getAllSpecies(): LiveData<List<Species>> = speciesRepository.getAllSpecies()
}