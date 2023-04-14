package edu.bu.tinypaws.room

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.bu.tinypaws.objects.Food
import edu.bu.tinypaws.objects.Pet

@Database(
    entities = [Pet::class, Food::class],
    version = 1,
    exportSchema = false
)

abstract class TinyPawDatabase:RoomDatabase() {
    abstract fun myPetDao(): MyPetDao
    abstract fun myFoodDao(): MyFoodDao
}