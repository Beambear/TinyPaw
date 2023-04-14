package edu.bu.tinypaws.room

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.bu.tinypaws.objects.Food
import edu.bu.tinypaws.objects.Pet

@Dao
interface MyFoodDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMyFood(food: Food)

    @Delete
    suspend fun removeMyFood(food: Food)

    @Update
    suspend fun updateFood(food:Food)

    @Query("SELECT * FROM myFoods")
    fun getAllMyFoods(): LiveData<List<Food>>

    @Query("SELECT name FROM myFoods")
    fun getAllMyFoodsName(): List<String>

    @Query("SELECT count(*) From myFoods")
    fun count(): LiveData<Int>

    @Query("SELECT * FROM myFoods where id =:foodId")
    fun getFoodById(foodId: Long): Food

    @Query("SELECT * FROM myFoods where name=:foodName")
    fun getFoodByName(foodName: String): Food
}