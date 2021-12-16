package com.example.spasdomuserapp.ui.services.planned.addplannedorder.category

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
import com.example.spasdomuserapp.models.CategoriesList
import com.example.spasdomuserapp.network.Resource
import com.example.spasdomuserapp.util.handleApiError
import com.example.spasdomuserapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlannedCategoriesFragment : Fragment() {

    private var plannedCategoriesAdapter: PlannedCategoriesAdapter? = null
    private val viewModel: CategoriesPlannedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPlannedCategoriesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_planned_categories, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.progressBar.visible(false)


        // TODO(Uncomment when you will receive categories from server)
        /*viewModel.getPlannedCategories()

        viewModel.plannedCategories.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visible(false)
                    plannedCategoriesAdapter?.categoriesLists = it.value
                }
                is Resource.Loading -> {
                    binding.progressBar.visible(true)
                }
                is Resource.Failure -> handleApiError(it) { }
            }
        }*/

        plannedCategoriesAdapter = PlannedCategoriesAdapter(PlannedCategoriesClick { category ->
            val action = PlannedCategoriesFragmentDirections.actionPlannedCategoriesFragmentToPlannedSubcategoryFragment(category, category.label)
            findNavController().navigate(action)
        })

        binding.root.findViewById<RecyclerView>(R.id.categories_rv).apply {
            layoutManager = GridLayoutManager(activity, 4)
            adapter = plannedCategoriesAdapter
        }

        // TODO(HARDCODE remove when REST is ready)
        plannedCategoriesAdapter?.categoriesLists = viewModel.categoriesLists

        return binding.root
    }
}

class PlannedCategoriesClick(val block: (CategoriesList) -> Unit) {
    fun onClick(categoriesList: CategoriesList) = block(categoriesList)
}