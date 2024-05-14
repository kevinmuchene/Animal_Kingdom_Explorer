package com.kevinmuchene.animalkingdomexplorer

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevinmuchene.animalkingdomexplorer.adapter.SpeciesAdapter
import com.kevinmuchene.animalkingdomexplorer.database.SpeciesDatabase
import com.kevinmuchene.animalkingdomexplorer.databinding.FragmentSpecialDetailsBinding
import com.kevinmuchene.animalkingdomexplorer.model.Species
import com.kevinmuchene.animalkingdomexplorer.repository.SpeciesRepository
import com.kevinmuchene.animalkingdomexplorer.viewmodel.SpeciesViewModel
import com.kevinmuchene.animalkingdomexplorer.viewmodel.SpeciesViewModelFactory

class SpeciesDetailsFragment : Fragment() {

    private lateinit var binding: FragmentSpecialDetailsBinding;
    private lateinit var speciesAdapter: SpeciesAdapter
    private lateinit var speciesViewModel: SpeciesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSpecialDetailsBinding.inflate(inflater, container, false);

        val database = SpeciesDatabase.invoke(requireContext())
        val repository = SpeciesRepository(database)

        val factory = SpeciesViewModelFactory(requireActivity().application, repository)
        speciesViewModel = ViewModelProvider(this, factory).get(SpeciesViewModel::class.java)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAnimalRecyclerView()

        binding.newsFabId.setOnClickListener {
            displayAnimalSpeciesDialog()
        }
    }

    private fun setupAnimalRecyclerView(){
        speciesAdapter = SpeciesAdapter()
        binding.speciesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = speciesAdapter
        }

        activity?.let {
            speciesViewModel.getAllSpecies().observe(viewLifecycleOwner) { species ->
                speciesAdapter.differ.submitList(species)
            }
        }
    }
    private fun displayAnimalSpeciesDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_animal_species_details, null)

        val speciesNameEditText = dialogView.findViewById<EditText>(R.id.speciesNameId)
        val speciesDescriptionEditText = dialogView.findViewById<EditText>(R.id.speciesDescriptionId)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add Species")


        dialogBuilder.setPositiveButton("Save") { dialog, which ->

            val speciesName = speciesNameEditText.text.toString().trim()
            val speciesDescription = speciesDescriptionEditText.text.toString().trim()

            if(speciesName.isNotEmpty() && speciesDescription.isNotEmpty()) {
                val newSpecies = Species(speciesName = speciesName, speciesDescription = speciesDescription)
                speciesViewModel.addSpecies(newSpecies)
            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }

        }


        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->

            dialog.dismiss()
        }


        val dialog = dialogBuilder.create()
        dialog.show()
    }

}