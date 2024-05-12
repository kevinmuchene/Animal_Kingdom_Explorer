package com.kevinmuchene.animalkingdomexplorer.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kevinmuchene.animalkingdomexplorer.repository.SpeciesRepository

class SpeciesViewModelFactory(val app: Application, private val speciesRepository: SpeciesRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SpeciesViewModel(app, speciesRepository) as T
    }
}