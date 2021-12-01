package com.example.spasdomuserapp.ui.services.planned.categories.lvl2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentPlannedCategoriesLvl2Binding
import com.example.spasdomuserapp.ui.home.NewsDetailedFragmentArgs
import com.example.spasdomuserapp.ui.services.planned.categories.PlannedCategoriesClick
import com.example.spasdomuserapp.ui.services.planned.categories.lvl1.PlannedCategoriesAdapter


class PlannedCategoriesLvl2Fragment : Fragment() {

    private var plannedCategoriesAdapter: PlannedCategoriesAdapter? = null
    private val args by navArgs<PlannedCategoriesLvl2FragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        plannedCategoriesAdapter?.plannedCategories = args.category.listLvl2 ?: listOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPlannedCategoriesLvl2Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_planned_categories_lvl2, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        plannedCategoriesAdapter = PlannedCategoriesAdapter(PlannedCategoriesClick {
            Log.i("click", it.toString())
        })

        binding.root.findViewById<RecyclerView>(R.id.categories_lvl2_rv).apply {
            layoutManager = GridLayoutManager(activity, 4)
            adapter = plannedCategoriesAdapter
        }

        return binding.root
    }
}

