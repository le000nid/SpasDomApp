package com.example.spasdomuserapp.ui.services.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentOrderDetailedBinding

class OrderDetailedFragment : Fragment(R.layout.fragment_order_detailed) {

    private val args by navArgs<OrderDetailedFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentOrderDetailedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_detailed, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.order = args.order

        return binding.root
    }
}