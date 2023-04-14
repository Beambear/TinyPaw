package edu.bu.tinypaws.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import edu.bu.tinypaws.R
import edu.bu.tinypaws.databinding.FragmentMyPetAddBinding
import edu.bu.tinypaws.databinding.FragmentMyPetEditBinding
import edu.bu.tinypaws.objects.Pet
import edu.bu.tinypaws.viewmodel.home.CurPetViewModel
import edu.bu.tinypaws.viewmodel.home.FoodRecyclerViewModel
import edu.bu.tinypaws.viewmodel.home.MyPetRecyclerViewViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyPetAddFragment: Fragment(), View.OnClickListener {

    private var _binding: FragmentMyPetAddBinding?= null
    private val binding get()= _binding!!

    private lateinit var myPetListViewModel: MyPetRecyclerViewViewModel
    private lateinit var petViewModel: CurPetViewModel
    private lateinit var foodListViewModel : FoodRecyclerViewModel
    private lateinit var foodSpinner:Spinner

    override fun onCreateView(
        inflater:LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?{
        _binding = FragmentMyPetAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view:View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        myPetListViewModel = ViewModelProvider(requireActivity()).get(MyPetRecyclerViewViewModel::class.java)
        petViewModel = ViewModelProvider(requireActivity()).get(CurPetViewModel::class.java)
        foodListViewModel = ViewModelProvider(requireActivity()).get(FoodRecyclerViewModel::class.java)
        foodSpinner = binding.myPetAddSpinner

        initSpinner()
        submit()
        binding.addCancelMyPet.setOnClickListener(this)
    }

    //initialize spinner
    private fun initSpinner() {

        CoroutineScope(Dispatchers.Default).launch {

            val foodOptions : List<String> = foodListViewModel.getAllMyFoodsName()
            val foodAdapter: ArrayAdapter<String>? =
                context?.let {
                    ArrayAdapter(it,android.R.layout.simple_spinner_item,foodOptions) }
            foodAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            foodAdapter?.notifyDataSetChanged()
            foodSpinner.adapter = foodAdapter
        }
    }

    private fun submit(){
        binding.addSubmitMyPet.setOnClickListener {
            val pet = Pet(
                0,
                binding.petAddNameContent.text.toString(),
                "none",//TODO
                binding.petAddBreederContent.text.toString(),
                binding.petAddSexContent.text.toString(),
                binding.petAddWeightContent.text.toString().toDouble(),
                binding.petAddBirthdayTitle.text.toString(),
                binding.myPetAddSpinner.selectedItem.toString(),
                binding.petAddIntroContext.text.toString())
            myPetListViewModel.addPet(pet)
            petViewModel.setCurPet(pet)
            view?.findNavController()?.navigate(R.id.action_myPetAddFragment_pop)
        }
    }

    override fun onClick(view: View) {
//        if(view.id == R.id.add_submit_my_pet){
//            val pet = Pet(
//                0,
//                binding.petAddNameContent.text.toString(),
//                "none",//TODO
//            binding.petAddBreederContent.text.toString(),
//                binding.petAddSexContent.text.toString(),
//                binding.petAddWeightContent.text.toString(),
//                binding.petAddBirthdayTitle.text.toString(),
//                binding.myPetAddSpinner.selectedItem.toString(),
//                binding.petAddIntroContext.text.toString()
//            )
//            myPetListViewModel.addPet(pet)
//            petViewModel.setCurPet(pet)
//        }
        view.findNavController().navigate(R.id.action_myPetAddFragment_pop)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}