package com.kevinmuchene.animalkingdomexplorer

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kevinmuchene.animalkingdomexplorer.databinding.FragmentSpecialDetailsBinding

class SpecialDetailsFragment : Fragment() {


    private lateinit var binding: FragmentSpecialDetailsBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSpecialDetailsBinding.inflate(inflater, container, false);

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newsFabId.setOnClickListener {
            displayAnimalSpeciesDialog()
        }
    }

    private fun displayAnimalSpeciesDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_animal_special_details, null)


        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add Species")


        dialogBuilder.setPositiveButton("Save") { dialog, which ->

        }


        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->

            dialog.dismiss()
        }


        val dialog = dialogBuilder.create()
        dialog.show()
    }

}