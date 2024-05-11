package com.kevinmuchene.animalkingdomexplorer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.kevinmuchene.animalkingdomexplorer.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.animalDetailsBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_animalDetailsFragment)
        }

        binding.speciesDetailsBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_specialDetailsFragment)
        }

        return binding.root
    }


}