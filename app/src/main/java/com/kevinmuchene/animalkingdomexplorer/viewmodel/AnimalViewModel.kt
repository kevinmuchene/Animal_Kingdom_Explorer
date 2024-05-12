package com.kevinmuchene.animalkingdomexplorer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kevinmuchene.animalkingdomexplorer.model.Animal
import com.kevinmuchene.animalkingdomexplorer.repository.AnimalRepository
import kotlinx.coroutines.launch

class AnimalViewModel(app: Application, private val animalRepository: AnimalRepository): AndroidViewModel(app) {

    fun addAnimal(animal: Animal) {
        viewModelScope.launch {
            animalRepository.addAnimal(animal)
        }
    }

    fun getAllAnimals(): LiveData<List<Animal>> = animalRepository.getAllAnimals()
}