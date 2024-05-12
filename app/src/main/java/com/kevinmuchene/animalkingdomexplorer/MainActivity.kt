package com.kevinmuchene.animalkingdomexplorer

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.kevinmuchene.animalkingdomexplorer.database.AnimalDatabase
import com.kevinmuchene.animalkingdomexplorer.database.SpeciesDatabase
import com.kevinmuchene.animalkingdomexplorer.databinding.ActivityMainBinding
import com.kevinmuchene.animalkingdomexplorer.repository.AnimalRepository
import com.kevinmuchene.animalkingdomexplorer.repository.SpeciesRepository
import com.kevinmuchene.animalkingdomexplorer.viewmodel.AnimalViewModel
import com.kevinmuchene.animalkingdomexplorer.viewmodel.AnimalViewModelFactory
import com.kevinmuchene.animalkingdomexplorer.viewmodel.SpeciesViewModel
import com.kevinmuchene.animalkingdomexplorer.viewmodel.SpeciesViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var animalViewModel: AnimalViewModel
    private lateinit var speciesViewModel: SpeciesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


        setupViewModel()

    }

    private fun setupViewModel() {
        val animalRepository = AnimalRepository(AnimalDatabase(this))
        val animalViewModelFactory = AnimalViewModelFactory(application, animalRepository)

        val speciesRepository = SpeciesRepository(SpeciesDatabase(this))
        val speciesViewModelFactory = SpeciesViewModelFactory(application, speciesRepository)


        animalViewModel = ViewModelProvider(this, animalViewModelFactory)[AnimalViewModel::class.java]
        speciesViewModel = ViewModelProvider(this, speciesViewModelFactory)[SpeciesViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}