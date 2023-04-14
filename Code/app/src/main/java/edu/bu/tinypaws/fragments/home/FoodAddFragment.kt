package edu.bu.tinypaws.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import edu.bu.tinypaws.R
import edu.bu.tinypaws.databinding.FragmentFoodAddBinding
import edu.bu.tinypaws.objects.Food
import edu.bu.tinypaws.viewmodel.home.CurFoodViewModel
import edu.bu.tinypaws.viewmodel.home.FoodRecyclerViewModel

class FoodAddFragment : Fragment(R.layout.fragment_food_add), View.OnClickListener {
    private var _binding: FragmentFoodAddBinding? =null
    private val binding get()=_binding!!

    private lateinit var foodViewModel: CurFoodViewModel
    private lateinit var foodListViewModel: FoodRecyclerViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view:View, savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
        foodViewModel = ViewModelProvider(requireActivity()).get(CurFoodViewModel::class.java)
        foodListViewModel = ViewModelProvider(requireActivity()).get(FoodRecyclerViewModel::class.java)

        binding.foodAddSubmit.setOnClickListener(this)
        binding.foodAddCancel.setOnClickListener(this)
    }


    private fun pop(view:View){
        view.findNavController().navigate(R.id.action_foodAddFragment_pop)
    }

    private fun submit(){
        var calorie:Int
        if(binding.foodAddCalorie.text.toString()==""){
            calorie =0
        }else{
            calorie = binding.foodAddCalorie.text.toString().toInt()
        }
        val food = Food(
            0,
            binding.foodAddName.text.toString(),
            binding.foodAddBrand.text.toString(),
            calorie)
        foodListViewModel.addFood(food)
        foodViewModel.setCurFood(food)
    }

    override fun onClick(view: View){
        if (view.id == R.id.food_add_submit){
            submit()
            pop(view)
        }else if( view.id == R.id.food_add_cancel){
            pop(view)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}