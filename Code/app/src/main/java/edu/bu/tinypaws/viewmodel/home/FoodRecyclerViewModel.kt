package edu.bu.tinypaws.viewmodel.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import edu.bu.tinypaws.TinyPawApplication
import edu.bu.tinypaws.objects.Food
import kotlinx.coroutines.launch

class FoodRecyclerViewModel (application: Application): AndroidViewModel(application){
    val myFoodRepository=
        (application as TinyPawApplication).myFoodRepository
    private val _foodList: LiveData<List<Food>> = myFoodRepository.getAllFoods()
    val foodList: LiveData<List<Food>>
    get() = _foodList

    fun getCount():LiveData<Int>{
        return myFoodRepository.count()
    }

    fun getAllMyFoodsName():List<String>{
        return  myFoodRepository.getAllMyFoodsName()
    }

    fun getAllFoods():LiveData<List<Food>>{
        return myFoodRepository.getAllFoods()
    }

    fun addFood(food:Food){
        viewModelScope.launch{
            myFoodRepository.addFood(food)
        }
    }

    fun removeFood(food:Food){
        viewModelScope.launch {
            myFoodRepository.removeFood(food)
        }
    }
}