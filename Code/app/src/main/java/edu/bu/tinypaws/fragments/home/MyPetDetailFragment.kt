package edu.bu.tinypaws.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import edu.bu.tinypaws.R
import edu.bu.tinypaws.databinding.FragmentMyPetDetailBinding
import edu.bu.tinypaws.viewmodel.home.CurFoodViewModel
import edu.bu.tinypaws.viewmodel.home.CurPetViewModel
import edu.bu.tinypaws.viewmodel.home.FoodRecyclerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyPetDetailFragment : Fragment(R.layout.fragment_my_pet_detail) {
    private var _binding: FragmentMyPetDetailBinding ?= null
    private val binding get() = _binding!!
    private lateinit var  petViewModel: CurPetViewModel
    private lateinit var foodListViewModel:FoodRecyclerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentMyPetDetailBinding.inflate(inflater,
        container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        petViewModel  = ViewModelProvider(requireActivity()).get(CurPetViewModel::class.java)
        foodListViewModel = ViewModelProvider(requireActivity()).get(FoodRecyclerViewModel::class.java)
        //set view model observer
        setObs()
        binding.editMyPet.setOnClickListener{
            view.findNavController().navigate(R.id.action_myPetDetailFragment_to_myPetEditFragment)
        }
    }


    private fun setObs(){
        var foodName:String = ""
        var weight:Double =0.0
        var foodWeight:String = ""
        var calorie:String = ""

        petViewModel.curPet.observe(viewLifecycleOwner) {
            binding.petDetailId.setText("#"+it.id.toString())
            binding.petDetailNameContent.setText(it.name)
//            binding.petDetailPhoto TOdo("can't save image now")
            binding.petDetailBreederContent.text = it.breeder
            binding.petDetailSexContent.setText(it.sex)
            binding.petDetailWeightContent.setText(it.weight.toString() +"kg")
            binding.petDetailBirthdayContent.setText((it.birthday))//TODO("try to change into Date")
            binding.petDetailCurrentFoodContent.text = it.currentFood  //TODO("set food class")
            binding.petDetailIntroContext.setText(it.intro)

            foodName = it.currentFood
            weight = it.weight

        }


        foodListViewModel.foodList.observe(viewLifecycleOwner){
            for(i in 0 until it.count()){
                if(it[i].name.equals(foodName)){
                    binding.petDetailFoodCalContext.setText(it[i].calPerUnit.toString()+" Kcal/kg")
                    var calIntake: Double = 1.2*(weight*30+70)*0.8          //calculate calorie intake per day
                    var foodIntake: Double = 1000*(calIntake)/it[i].calPerUnit //calculate food weight per day
                    binding.petDetailFoodWeightContext.setText(foodIntake.toInt().toString()+" g/day")
                    binding.petDetailCalIntakeTitle.setText(calIntake.toInt().toString()+" kcal/day")
                }
            }
        }
    }

}