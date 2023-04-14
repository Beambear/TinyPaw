package edu.bu.tinypaws.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.bu.tinypaws.R
import edu.bu.tinypaws.adapter.MyPetCardAdapter
import edu.bu.tinypaws.databinding.FragmentMyPetSlidepaneBinding
import edu.bu.tinypaws.objects.Pet

class MyPetSlidePaneFragment : Fragment(), MyPetCardAdapter.OnPetClickListener {
    private var _binding: FragmentMyPetSlidepaneBinding?= null
    private val binding get() = _binding!!

//    override fun onCreate(savedInstanceState: Bundle?){
//        super.onCreate(savedInstanceState)
//        binding.myPetListFragment.setOnClickListener{
//            binding.myPetSlidePane.open()
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_pet_slidepane, container, false)
    }

    override fun onPetClick(pet: Pet) {
        binding.myPetSlidePane.open()
    }
}