package com.kevinmuchene.animalkingdomexplorer

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.kevinmuchene.animalkingdomexplorer.databinding.FragmentAnimalDetailsBinding

class AnimalDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAnimalDetailsBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAnimalDetailsBinding.inflate(inflater, container, false);

        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newsFabId.setOnClickListener {
            displayAnimalDetailsDialog()
        }
    }

    private fun displayAnimalDetailsDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_animal_details, null)


        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Animal Details")


        dialogBuilder.setPositiveButton("Save") { dialog, which ->

        }


        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->

            dialog.dismiss()
        }


        val dialog = dialogBuilder.create()
        dialog.show()
    }

}