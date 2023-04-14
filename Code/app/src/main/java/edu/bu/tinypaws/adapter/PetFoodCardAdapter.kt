package edu.bu.tinypaws.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.bu.tinypaws.databinding.CardPetFoodListBinding
import edu.bu.tinypaws.objects.Food

class PetFoodCardAdapter(
    private val onFoodClickListener: OnFoodClickListener
):RecyclerView.Adapter<PetFoodCardViewHolder>() {

    private val foods = mutableListOf<Food>()   //store source data

    //update source data content
    fun updateFoods(curFoods: List<Food>){
        foods.clear()
        foods.addAll(curFoods)
        notifyDataSetChanged()
    }

    //create view holder widget for each item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetFoodCardViewHolder{
        return PetFoodCardViewHolder(
            CardPetFoodListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    //get view holder for each item, and the position of the item as arguments.
    override fun onBindViewHolder(holder:PetFoodCardViewHolder, position: Int){
        val food = foods[position]
        holder.bindFood(food)
        holder.cardView.setOnClickListener{
            onFoodClickListener.onFoodClick(food)
        }
    }

    //get list count
    override fun getItemCount(): Int = foods.size

    //getter
    fun getFood(pos:Int):Food{
        if(foods.size>0)
            return foods[pos]
        else
            return Food(0,"","",0)
    }
    interface OnFoodClickListener{
        fun onFoodClick(food: Food)
    }
}

