package edu.bu.tinypaws.objects

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName="myFoods")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var name: String,
    var brand: String,
    var calPerUnit: Int,
//    var photo: ?? //TODO
){
    override fun toString(): String {
        return name
    }

}
