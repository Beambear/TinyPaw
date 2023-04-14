package edu.bu.tinypaws.adapter

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import edu.bu.tinypaws.databinding.CardPetFoodListBinding
import edu.bu.tinypaws.objects.Food

class PetFoodCardViewHolder(
    private val cardPetFoodBinding:CardPetFoodListBinding
): RecyclerView.ViewHolder(cardPetFoodBinding.root) {

    val cardView: CardView = cardPetFoodBinding.petFoodCard

    fun bindFood(food:Food){
        cardPetFoodBinding.cardPetFoodFoodId.text= food.id.toString()
        cardPetFoodBinding.cardPetFoodName.text = food.name
        cardPetFoodBinding.cardPetFoodBrand.text = food.brand
        cardPetFoodBinding.cardPetFoodCalorie.text = food.calPerUnit.toString()
        //photo//TODO
    }
}