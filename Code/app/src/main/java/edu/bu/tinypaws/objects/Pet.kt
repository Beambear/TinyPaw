package edu.bu.tinypaws.objects

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
@Entity(tableName="myPets")
data class Pet(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String,
    var photo: String,//for now
    var breeder: String,
    var sex: String,
    var weight: Double,
    var birthday: String,
    var currentFood: String,    //TODO (" string -> @foreignKey currentFood:Food.class")
    var intro: String,
//    @Ignore var dailyFoodWeight:String,
//    @Ignore var dailyCalorie:String

){

}
