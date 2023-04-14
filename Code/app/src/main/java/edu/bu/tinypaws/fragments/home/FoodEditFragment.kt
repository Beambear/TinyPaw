package edu.bu.tinypaws.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import edu.bu.tinypaws.R
import edu.bu.tinypaws.databinding.FragmentFoodEditBinding
import edu.bu.tinypaws.viewmodel.home.CurFoodViewModel
import edu.bu.tinypaws.viewmodel.home.FoodRecyclerViewModel

class FoodEditFragment : Fragment(R.layout.fragment_food_edit){
    private var _binding: FragmentFoodEditBinding? = null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodEditBinding.inflate(inflater,
            container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity()).get(CurFoodViewModel::class.java)
        curFoodObs(viewModel)   //set view model observer
        submit(viewModel)   //submit button action
        cancel()    //cancel button action

        removeFood(viewModel)
    }

    private fun removeFood(viewModel:CurFoodViewModel){
        binding.foodEditRemove.setOnClickListener{
            val listViewModel = ViewModelProvider(requireActivity()).get(FoodRecyclerViewModel::class.java)
            listViewModel.removeFood(viewModel.getCurFood())
            view?.findNavController()?.navigate(R.id.action_foodEditFragment_pop)
        }
    }

    private fun cancel(){
        binding.foodEditCancel.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_foodEditFragment_pop)
        }
    }

    private fun submit(viewModel: CurFoodViewModel){
        binding.foodEditSubmit.setOnClickListener{
            val calorie = binding.foodEditCalorie.text.toString()
            viewModel.updateCurFood(
                binding.foodEditName.text.toString(),
                binding.foodEditBrand.text.toString(),
                calorie.toInt()
            )
            view?.findNavController()?.navigate(R.id.action_foodEditFragment_pop)
        }
    }

    private fun curFoodObs(viewModel: CurFoodViewModel){
        viewModel.curFood.observe(viewLifecycleOwner) {
            binding.foodEditId.text= "#"+it.id.toString()
            binding.foodEditName.setText(it.name)
//            binding.petDetailPhoto TOdo("can't save image now")
            binding.foodEditBrand.setText(it.brand)
            binding.foodEditCalorie.setText(it.calPerUnit.toString())
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}