package edu.bu.tinypaws.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import edu.bu.tinypaws.databinding.CardMyPetListBinding
import edu.bu.tinypaws.objects.Pet

class MyPetCardViewHolder(
    private val cardMyPetBinding: CardMyPetListBinding
): RecyclerView.ViewHolder(cardMyPetBinding.root) {

    val cardView: CardView = cardMyPetBinding.myPetCard
//    val idView : TextView = cardMyPetBinding.cardMyPetPetId
////    val photoView : ImageView         TODO("set photo as string for now")
//    val nameView : TextView = cardMyPetBinding.cardMyPetName
//    val breederView: TextView = cardMyPetBinding.cardMyPetBreeder

    fun bindPet(pet:Pet){
        cardMyPetBinding.cardMyPetPetId.text = pet.id.toString()
//        TODO("set photo as string for now")
//        photoView.setImageResource(pet.photo)
        cardMyPetBinding.cardMyPetName.text = pet.name
        cardMyPetBinding.cardMyPetBreeder.text = pet.breeder

    }


}