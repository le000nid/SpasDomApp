package com.example.spasdomuserapp.ui.services.planned.categories.lvl1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentPlannedCategoriesBinding
import com.example.spasdomuserapp.models.PlannedCategory
import com.example.spasdomuserapp.ui.services.planned.categories.AddOrderViewModel

class PlannedCategoriesFragment : Fragment() {

    private var plannedCategoriesAdapter: PlannedCategoriesAdapter? = null
    private val viewModel: AddOrderViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        plannedCategoriesAdapter?.plannedCategories = viewModel.plannedCategories
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPlannedCategoriesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_planned_categories, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        plannedCategoriesAdapter = PlannedCategoriesAdapter(PlannedCategoriesClick {
            val action = PlannedCategoriesFragmentDirections.actionPlannedCategoriesFragmentToPlannedCategoriesLvl2Fragment(it, it.label)
            findNavController().navigate(action)
        })

        binding.root.findViewById<RecyclerView>(R.id.categories_rv).apply {
            layoutManager = GridLayoutManager(activity, 4)
            adapter = plannedCategoriesAdapter
        }

        return binding.root
    }
}

class PlannedCategoriesClick(val block: (PlannedCategory) -> Unit) {
    fun onClick(plannedCategory: PlannedCategory) = block(plannedCategory)
}