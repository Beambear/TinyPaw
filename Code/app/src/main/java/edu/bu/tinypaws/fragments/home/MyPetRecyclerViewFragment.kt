package edu.bu.tinypaws.fragments.home

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.bu.tinypaws.R
import edu.bu.tinypaws.adapter.MyPetCardAdapter
import edu.bu.tinypaws.databinding.FragmentMyPetRecyclerViewBinding
import edu.bu.tinypaws.objects.Pet
import edu.bu.tinypaws.viewmodel.home.MyPetRecyclerViewViewModel
import edu.bu.tinypaws.viewmodel.home.CurPetViewModel

class MyPetRecyclerViewFragment : Fragment() {
    private var _binding: FragmentMyPetRecyclerViewBinding?= null
    private val binding get() = _binding!!

    private lateinit var petAdapter: MyPetCardAdapter
    private lateinit var curPetViewModel: CurPetViewModel
    private lateinit var petListViewModel: MyPetRecyclerViewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPetRecyclerViewBinding.inflate(inflater,container,false)

        binding.myPetAddFab.setOnClickListener{
            it.findNavController().navigate(R.id.action_myPetRecyclerViewFragment_to_myPetAddFragment)
        }
        binding.myPetGoFoodButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_navigation_my_pet_to_foodRecyclerViewFragment)
        }

        return binding.root
//        below do the same as above
//        return inflater.inflate(R.layout.fragment_my_pet_recycler_view, container, false)
    }

    override fun onViewCreated(view:View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view,savedInstanceState)

        curPetViewModel =
            ViewModelProvider(requireActivity()).get(CurPetViewModel::class.java)
        petListViewModel =
            ViewModelProvider(this).get(MyPetRecyclerViewViewModel::class.java)

        binding.myPetList.apply {
            layoutManager = LinearLayoutManager(context) //layout management
            petAdapter = MyPetCardAdapter(  //set listed cards click actions
                object: MyPetCardAdapter.OnPetClickListener{
                    override fun onPetClick(pet: Pet) {
                        curPetViewModel.setCurPet(pet)
                        view.findNavController().navigate(R.id.action_myPetRecyclerViewFragment_to_myPetDetailFragment)
                    }
                }
            )
            this.adapter = petAdapter //attach adapter to the recyclerView

            //view model initial pass
            petListViewModel.petList.observe(viewLifecycleOwner) {
                petAdapter.updatePets(it)
                curPetViewModel.initCurPet(petAdapter.getPet(0))
            }

            curPetViewModel.curPet.observe(viewLifecycleOwner) {
                petAdapter.notifyDataSetChanged()
            }

        }
    }
}