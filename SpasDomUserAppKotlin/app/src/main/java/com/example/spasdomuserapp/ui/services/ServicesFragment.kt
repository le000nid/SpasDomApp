package com.example.spasdomuserapp.ui.services

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentServicesBinding
import com.google.android.material.tabs.TabLayoutMediator

class ServicesFragment : Fragment(R.layout.fragment_services) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentServicesBinding.bind(view)

        binding.apply {
            val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
            viewPager2.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                when(position) {
                    0 -> tab.text = "Экстренные"
                    1 -> tab.text = "Плановые"
                    2 -> tab.text = "Доп. услуги"
                }
            }.attach()
        }
    }
}