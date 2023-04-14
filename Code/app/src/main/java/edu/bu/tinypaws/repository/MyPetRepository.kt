package edu.bu.tinypaws.repository

import androidx.lifecycle.LiveData
import edu.bu.tinypaws.objects.Pet
import edu.bu.tinypaws.room.MyPetDao
import java.util.concurrent.Executors

class MyPetRepository (private val myPetDao: MyPetDao){
//    val executor = Executors.newSingleThreadExecutor()

    suspend fun addMyPet(pet:Pet){
//        executor.execute{
            myPetDao.addPet(pet)
//        }
    }

    suspend fun removeMyPet(pet:Pet){
//        executor.execute{
            myPetDao.removePet(pet)
//        }
    }

    suspend fun updatePet(pet:Pet){
//        executor.execute{
            myPetDao.updatePet(pet)
//        }
    }

    fun getAllMyPets(): LiveData<List<Pet>> {
        return myPetDao.getAllMyPets()
    }

    fun count(): LiveData<Int>{
        return myPetDao.count()
    }


}