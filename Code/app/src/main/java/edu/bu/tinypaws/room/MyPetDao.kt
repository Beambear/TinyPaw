package edu.bu.tinypaws.room

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.bu.tinypaws.objects.Pet

@Dao
interface MyPetDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPet(myPet: Pet)

    @Delete
    suspend fun removePet(pet:Pet)

    @Update
    suspend fun updatePet(pet:Pet)

    @Query("SELECT * FROM myPets")
    fun getAllMyPets(): LiveData<List<Pet>>

    @Query("SELECT count(*) From myPets")
    fun count(): LiveData<Int>

    @Query("SELECT * FROM myPets where id =:petId")
    fun getPetById(petId: Long): LiveData<Pet>
}