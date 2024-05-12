package com.kevinmuchene.animalkingdomexplorer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kevinmuchene.animalkingdomexplorer.databinding.AnimalLayoutBinding
import com.kevinmuchene.animalkingdomexplorer.model.Animal

class AnimalAdapter : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(val itemBinding: AnimalLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Animal>() {
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {

            return oldItem.id == newItem.id
                    && oldItem.name == newItem.name
                    && oldItem.habitat == newItem.habitat
                    && oldItem.diet == newItem.diet
        }

        override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {

            return oldItem == newItem
        }

    }

    var differ = AsyncListDiffer(this, differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {

        return AnimalViewHolder(
            AnimalLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {

        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {

        val currentAnimal = differ.currentList[position]

        holder.itemBinding.animalNameAdapt.text = currentAnimal.name
        holder.itemBinding.animalHabitatAdapt.text = currentAnimal.habitat
        holder.itemBinding.animalDietAdapt.text = currentAnimal.diet
    }
}