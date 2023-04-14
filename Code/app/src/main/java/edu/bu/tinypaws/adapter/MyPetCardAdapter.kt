package edu.bu.tinypaws.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.bu.tinypaws.databinding.CardMyPetListBinding
import edu.bu.tinypaws.objects.Pet

class MyPetCardAdapter(
    private val onPetClickListener: OnPetClickListener)
    : RecyclerView.Adapter<MyPetCardViewHolder>() {

    private val pets = mutableListOf<Pet>() //store source data

    //update source data content
    fun updatePets(curPets: List<Pet>){
        pets.clear()
        pets.addAll(curPets)
        notifyDataSetChanged()
    }

    // create view holder widget for each item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPetCardViewHolder
    {
//        val from = LayoutInflater.from(parent.context)
//        val binding = CardMyPetListBinding.inflate(from,parent,false)
//        return MyPetCardViewHolder(binding)
        //above can also write as below
        return MyPetCardViewHolder(
            CardMyPetListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    // get view holder for each item, and the position of the item as arguments.
    override fun onBindViewHolder(holder: MyPetCardViewHolder, position: Int) {
        val pet = pets[position]
        holder.bindPet(pet)
        holder.cardView.setOnClickListener{
            onPetClickListener.onPetClick(pet)
        }
    }

    override fun getItemCount(): Int = pets.size

    fun getPet(pos:Int):Pet{
        if(pets.size>0)
            return pets[pos]
        else
            return Pet(0,"","","","",0.0,"","","")
    }

    interface OnPetClickListener{
        fun onPetClick(pet:Pet)
    }

}