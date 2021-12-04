package com.example.spasdomuserapp.ui.services.planned.categories.date

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentPlannedDateBinding


class PlannedDateFragment : Fragment(R.layout.fragment_planned_date) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPlannedDateBinding.bind(view)

    }
}