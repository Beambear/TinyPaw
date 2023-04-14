package edu.bu.tinypaws.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.bu.tinypaws.R
import edu.bu.tinypaws.adapter.PetFoodCardAdapter
import edu.bu.tinypaws.databinding.FragmentFoodRecyclerViewBinding
import edu.bu.tinypaws.objects.Food
import edu.bu.tinypaws.objects.Pet
import edu.bu.tinypaws.viewmodel.home.CurFoodViewModel
import edu.bu.tinypaws.viewmodel.home.FoodRecyclerViewModel

class FoodRecyclerViewFragment: Fragment() {
    private var _binding: FragmentFoodRecyclerViewBinding?=null
    private val binding get() = _binding!!

    private lateinit var foodAdapter: PetFoodCardAdapter
    private lateinit var curFoodViewModel: CurFoodViewModel
    private lateinit var foodListViewModel: FoodRecyclerViewModel
    private lateinit var onFoodClickListener: PetFoodCardAdapter.OnFoodClickListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        _binding = FragmentFoodRecyclerViewBinding.inflate(inflater,container,false)
        binding.foodGoMyPetButton.setOnClickListener{ it.findNavController().navigate(R.id.action_food_list_fragment_pop) }
        binding.petFoodAddFab.setOnClickListener{ it.findNavController().navigate(R.id.action_foodRecyclerViewFragment_to_foodAddFragment) }

//        TODO(spinner collect data from DB food table)

        return binding.root
    }

    override fun onViewCreated(view:View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        curFoodViewModel = ViewModelProvider(requireActivity()).get(CurFoodViewModel::class.java)
        foodListViewModel = ViewModelProvider(this).get(FoodRecyclerViewModel::class.java)

        binding.petFoodList.apply{
            layoutManager = LinearLayoutManager(context) //layout management

            foodAdapter = PetFoodCardAdapter(
                object:PetFoodCardAdapter.OnFoodClickListener{
                    override fun onFoodClick(food: Food) {
                        curFoodViewModel.setCurFood(food)
                        view.findNavController().navigate(R.id.action_foodRecyclerViewFragment_to_foodEditFragment)
                    }
                }
            )
            this.adapter = foodAdapter  //attach adapter to the recyclerview

            //initialize view model
            foodListViewModel.foodList.observe(viewLifecycleOwner){
                foodAdapter.updateFoods(it)
                curFoodViewModel.initCurFood(foodAdapter.getFood(0))
            }

            curFoodViewModel.curFood.observe(viewLifecycleOwner){
                foodAdapter.notifyDataSetChanged()
            }

        }
    }
}