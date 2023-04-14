package edu.bu.tinypaws.fragments.explore

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.bu.tinypaws.R
import edu.bu.tinypaws.viewmodel.explore.ExploreRecyclerViewViewModel

class ExploreRecyclerViewFragment : Fragment() {

    companion object {
        fun newInstance() = ExploreRecyclerViewFragment()
    }

    private lateinit var viewModel: ExploreRecyclerViewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_explore_recycler_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ExploreRecyclerViewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}