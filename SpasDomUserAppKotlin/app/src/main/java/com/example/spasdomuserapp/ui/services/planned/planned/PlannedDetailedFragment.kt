package com.example.spasdomuserapp.ui.services.planned.planned

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentPlannedDetailedBinding

class PlannedDetailedFragment : Fragment(R.layout.fragment_planned_detailed) {

    private val args by navArgs<PlannedDetailedFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPlannedDetailedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_planned_detailed, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.order = args.plannedOrder

        return binding.root
    }
}