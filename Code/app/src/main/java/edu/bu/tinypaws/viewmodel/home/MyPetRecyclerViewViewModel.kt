package edu.bu.tinypaws.viewmodel.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import edu.bu.tinypaws.TinyPawApplication
import edu.bu.tinypaws.objects.Pet
import kotlinx.coroutines.launch

class MyPetRecyclerViewViewModel (application: Application) : AndroidViewModel(application) {
    // pass the TinyPawApplication as a parameter
    // need to define the application name in the manifest.
    val myPetRepository =
        (application as TinyPawApplication).myPetRepository

    private val _petList: LiveData<List<Pet>> = myPetRepository.getAllMyPets()
    val petList: LiveData<List<Pet>>
    get() = _petList


    fun getAllPets(): LiveData<List<Pet>>{
        return myPetRepository.getAllMyPets()
    }

    fun addPet(pet:Pet){
        viewModelScope.launch{
            myPetRepository.addMyPet(pet)
        }
    }

    fun removePet(pet:Pet){
        viewModelScope.launch {
            myPetRepository.removeMyPet(pet)
        }
    }

}
