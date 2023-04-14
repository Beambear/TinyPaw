package edu.bu.tinypaws.viewmodel.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import edu.bu.tinypaws.TinyPawApplication
import edu.bu.tinypaws.objects.Food
import kotlinx.coroutines.launch

class CurFoodViewModel (application :Application):AndroidViewModel(application){
    private val _curFood: MutableLiveData<Food> = MutableLiveData()
    val curFood:LiveData<Food>
    get()= _curFood

    val myFoodRepository = (application as TinyPawApplication).myFoodRepository

    fun initCurFood(food:Food){
        if(_curFood.value == null)
            _curFood.value == food
    }

    fun setCurFood(food:Food){
        _curFood.value = food
    }

    fun setCurFoodById(foodId:Long){
        _curFood.value = myFoodRepository.getFoodById(foodId)
    }

    fun setCurFoodByName(foodName:String) {
        _curFood.value == myFoodRepository.getFoodByName(foodName)
    }

    fun getCurFood():Food{
        return _curFood.value!!
    }

    fun isCurFood(food:Food):Boolean{
        return _curFood.value?.id == food.id
    }

    fun updateCurFood(name: String, brand: String, calorie:Int){
        _curFood.value = _curFood.value?.apply{
            this.name = name
            this.brand = brand
            this.calPerUnit = calorie
        }
        viewModelScope.launch{
            myFoodRepository.updateFood(_curFood.value!!)
        }

    }
}