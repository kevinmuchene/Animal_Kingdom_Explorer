package com.kevinmuchene.animalkingdomexplorer

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kevinmuchene.animalkingdomexplorer.adapter.AnimalAdapter
import com.kevinmuchene.animalkingdomexplorer.database.AnimalDatabase
import com.kevinmuchene.animalkingdomexplorer.databinding.FragmentAnimalDetailsBinding
import com.kevinmuchene.animalkingdomexplorer.model.Animal
import com.kevinmuchene.animalkingdomexplorer.repository.AnimalRepository
import com.kevinmuchene.animalkingdomexplorer.viewmodel.AnimalViewModel
import com.kevinmuchene.animalkingdomexplorer.viewmodel.AnimalViewModelFactory

class AnimalDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAnimalDetailsBinding;
    private lateinit var animalAdapter: AnimalAdapter
    private lateinit var animalViewModel: AnimalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimalDetailsBinding.inflate(inflater, container, false);

        val database = AnimalDatabase.invoke(requireContext())
        val repository = AnimalRepository(database)


        val factory = AnimalViewModelFactory(requireActivity().application, repository)
        animalViewModel = ViewModelProvider(this, factory).get(AnimalViewModel::class.java)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAnimalRecyclerView()

        binding.newsFabId.setOnClickListener {
            displayAnimalDetailsDialog()
        }
    }

    private fun setupAnimalRecyclerView(){
        animalAdapter = AnimalAdapter()
        binding.animalRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = animalAdapter
        }

        activity?.let {
            animalViewModel.getAllAnimals().observe(viewLifecycleOwner) { animal ->
                animalAdapter.differ.submitList(animal)
            }
        }
    }

    private fun displayAnimalDetailsDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_animal_details, null)
        val animalNameEditText = dialogView.findViewById<EditText>(R.id.animalName)
        val animalHabitatEditText = dialogView.findViewById<EditText>(R.id.animalHabitat)
        val animalDietEditText = dialogView.findViewById<EditText>(R.id.animalDiet)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Animal Details")

        dialogBuilder.setPositiveButton("Save") { dialog, which ->
            val name = animalNameEditText.text.toString().trim()
            val habitat = animalHabitatEditText.text.toString().trim()
            val diet = animalDietEditText.text.toString().trim()

            if (name.isNotEmpty() && habitat.isNotEmpty() && diet.isNotEmpty()) {
                val newAnimal = Animal(name = name, habitat = habitat, diet = diet)
                animalViewModel.addAnimal(newAnimal)

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