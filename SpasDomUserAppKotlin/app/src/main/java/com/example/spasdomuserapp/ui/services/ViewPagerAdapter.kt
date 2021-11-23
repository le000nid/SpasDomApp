package com.example.spasdomuserapp.ui.services

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.spasdomuserapp.ui.orders.EmergencyFragment
import com.example.spasdomuserapp.ui.services.additionals.AdditionalFragment
import com.example.spasdomuserapp.ui.services.planned.PlannedFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> EmergencyFragment()
            1 -> PlannedFragment()
            2 -> AdditionalFragment()
            else -> Fragment()
        }
    }
}