package edu.bu.tinypaws.viewmodel.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import edu.bu.tinypaws.TinyPawApplication
import edu.bu.tinypaws.objects.Pet
import kotlinx.coroutines.launch

class CurPetViewModel(application: Application): AndroidViewModel(application) {
    private val _curPet: MutableLiveData<Pet> = MutableLiveData()
    val curPet: LiveData<Pet>
        get()=_curPet

    val MyPetRepository = (application as TinyPawApplication).myPetRepository

    fun initCurPet(pet:Pet){
        if(_curPet.value == null)
            _curPet.value == pet
    }

    fun setCurPet( pet: Pet){
        _curPet.value = pet
    }

    fun getCurPet(): Pet{
        return _curPet.value!!
    }

    fun isCurPet(pet:Pet):Boolean{
        return _curPet.value?.id == pet.id
    }


    fun updateCurPet(name: String, photo: String, breeder: String, sex: String, weight: Double, birthday: String, food:String, intro:String){
        _curPet.value = _curPet.value?.apply {
            this.name = name
            this.photo = photo
            this.breeder = breeder
            this.sex = sex
            this.weight = weight
            this.birthday = birthday
            this.currentFood = food
            this.intro = intro
        }
        viewModelScope.launch{
            MyPetRepository.updatePet(_curPet.value!!)
        }
    }
}