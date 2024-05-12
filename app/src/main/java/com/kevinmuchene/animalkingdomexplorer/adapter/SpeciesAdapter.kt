package com.kevinmuchene.animalkingdomexplorer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kevinmuchene.animalkingdomexplorer.databinding.SpeciesLayoutBinding
import com.kevinmuchene.animalkingdomexplorer.model.Species

class SpeciesAdapter : RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder>() {

    class SpeciesViewHolder(val itemBinding: SpeciesLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Species>() {
        override fun areItemsTheSame(oldItem: Species, newItem: Species): Boolean {

            return oldItem.id == newItem.id
                    && oldItem.speciesName == newItem.speciesName
                    && oldItem.speciesDescription == newItem.speciesDescription
        }

        override fun areContentsTheSame(oldItem: Species, newItem: Species): Boolean {

            return oldItem == newItem
        }

    }

    var differ = AsyncListDiffer(this, differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {

        return SpeciesViewHolder(
            SpeciesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {

        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {

        val currentSpecies = differ.currentList[position]

        holder.itemBinding.speciesNameAdapt.text = currentSpecies.speciesName
        holder.itemBinding.speciesNameAdapt.text = currentSpecies.speciesDescription
    }


}