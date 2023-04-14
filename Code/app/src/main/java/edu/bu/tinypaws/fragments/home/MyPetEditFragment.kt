package edu.bu.tinypaws.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import edu.bu.tinypaws.R
import edu.bu.tinypaws.databinding.FragmentMyPetEditBinding
import edu.bu.tinypaws.objects.Food
import edu.bu.tinypaws.viewmodel.home.CurFoodViewModel
import edu.bu.tinypaws.viewmodel.home.CurPetViewModel
import edu.bu.tinypaws.viewmodel.home.FoodRecyclerViewModel
import edu.bu.tinypaws.viewmodel.home.MyPetRecyclerViewViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyPetEditFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentMyPetEditBinding? = null
    private val binding get() = _binding!!
    private lateinit var myPetViewModel:CurPetViewModel
    private lateinit var foodViewModel: CurFoodViewModel
    private lateinit var foodListViewModel :FoodRecyclerViewModel
    private lateinit var foodSpinner :Spinner
    private lateinit var defaultFoodName:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        _binding = FragmentMyPetEditBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view:View,savedInstanceState:Bundle?){
        super.onViewCreated(view,savedInstanceState)

        myPetViewModel = ViewModelProvider(requireActivity()).get(CurPetViewModel::class.java)
        foodViewModel = ViewModelProvider(this).get(CurFoodViewModel::class.java)
        foodListViewModel = ViewModelProvider(requireActivity()).get(FoodRecyclerViewModel::class.java)
        foodSpinner = binding.myPetEditSpinner

        curPetObs()   //set viewModel observer
        submit()  //submit button action

        binding.editCancelMyPet.setOnClickListener{
            view.findNavController().navigate(R.id.action_myPetEditFragment_pop)
        }
        binding.editRemoveMyPet.setOnClickListener{
            removePet()
            view.findNavController().navigate(R.id.action_myPetEditFragment_to_navigation_my_pet)
        }
    }

    //initialize spinner
    private fun initSpinner() {

        CoroutineScope(Dispatchers.Default).launch {
            val foodOptions : List<String> = foodListViewModel.getAllMyFoodsName()  //TODO(can't have other observer in an observer, so just created this method rather than observing it)
            val foodAdapter: ArrayAdapter<String>? =
                context?.let {
                    ArrayAdapter(it,android.R.layout.simple_spinner_item,foodOptions) }
            foodAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            foodAdapter?.notifyDataSetChanged()
            foodSpinner.adapter = foodAdapter
            defaultSpinnerValue(foodOptions.size)
        }
        binding.myPetEditSpinner.onItemSelectedListener = this
    }

    //remove cur pet from db
    private fun removePet(){
        val petListViewModel = ViewModelProvider(requireActivity()).get(MyPetRecyclerViewViewModel::class.java)
        petListViewModel.removePet(myPetViewModel.getCurPet())
    }

    //set cur project observer
    private fun curPetObs(){
        myPetViewModel.curPet.observe(viewLifecycleOwner) {
            binding.petEditId.text = "#" + it.id.toString()
            binding.petEditNameContent.setText(it.name)
//            binding.petEditPhoto TOdo("can't save image now")
            binding.petEditBreederContent.setText(it.breeder)
            binding.petEditSexContent.setText(it.sex)
            binding.petEditWeightContent.setText(it.weight.toString())
            binding.petEditBirthdayContent.setText(it.birthday)//TODO("try to change into Date")
            defaultFoodName=it.currentFood  //get food spinner default select name
            binding.petEditIntroContext.setText(it.intro)
            initSpinner()   //initialize spinner    //TODO("this method have to stay here， defaultFoodName not works outside of s observer")
        }
    }

    //set spinner default value
    private fun defaultSpinnerValue(count:Int){
        for(i in 0 until count){
            if (defaultFoodName.equals(foodSpinner.adapter.getItem(i).toString())){
                foodSpinner.setSelection(i)
                break
            }
        }
    }

    //update submitted info
    private fun submit(){
        binding.editSubmitMyPet.setOnClickListener {
            //update new values
            myPetViewModel.updateCurPet(
                binding.petEditNameContent.text.toString(),
                "none",//TODO("photo no completed yet")
                binding.petEditBreederContent.text.toString(),
                binding.petEditSexContent.text.toString(),
                binding.petEditWeightContent.text.toString().toDouble(),
                binding.petEditBirthdayContent.text.toString(),//TODO("set to Date")
                binding.myPetEditSpinner.selectedItem.toString(),
                binding.petEditIntroContext.text.toString()
            )
            view?.findNavController()?.navigate(R.id.action_myPetEditFragment_pop)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateFoodCalculateResult(){
        foodViewModel.curFood.observe(viewLifecycleOwner){
            binding.petEditFoodCalContext.setText(it.calPerUnit.toString())
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(view == foodSpinner.selectedItem){
            foodViewModel.setCurFoodById(id)  //Set food viewmodel to spinner selected item
            updateFoodCalculateResult() //TODO(notify data change)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

}