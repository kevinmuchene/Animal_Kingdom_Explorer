package com.kevinmuchene.animalkingdomexplorer.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kevinmuchene.animalkingdomexplorer.repository.AnimalRepository

class AnimalViewModelFactory(val app: Application, private val animalRepository: AnimalRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimalViewModel(app, animalRepository) as T
    }
}