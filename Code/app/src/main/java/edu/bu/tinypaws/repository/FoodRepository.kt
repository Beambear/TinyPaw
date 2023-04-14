package edu.bu.tinypaws.repository

import androidx.lifecycle.LiveData
import edu.bu.tinypaws.objects.Food
import edu.bu.tinypaws.room.MyFoodDao

class FoodRepository(
    private val foodDao:MyFoodDao
) {
    suspend fun addFood(food:Food){
        foodDao.addMyFood(food)
    }

    suspend fun removeFood(food: Food){
        foodDao.removeMyFood(food)
    }

    suspend fun updateFood(food:Food){
        foodDao.updateFood(food)
    }

    fun getAllFoods():LiveData<List<Food>>{
        return foodDao.getAllMyFoods()
    }

    fun getAllMyFoodsName():List<String>{
        return foodDao.getAllMyFoodsName()
    }

    fun count(): LiveData<Int> {
        return foodDao.count()
    }

    fun getFoodById(foodId:Long):Food{
        return foodDao.getFoodById(foodId)
    }

    fun getFoodByName(foodName:String):Food{
        return foodDao.getFoodByName(foodName)
    }

}