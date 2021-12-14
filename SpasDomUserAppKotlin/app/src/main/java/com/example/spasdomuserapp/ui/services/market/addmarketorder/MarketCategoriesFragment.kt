package com.example.spasdomuserapp.ui.services.market.addmarketorder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentMarketCategoriesBinding
import com.example.spasdomuserapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarketCategoriesFragment : Fragment() {

    private val viewModel: MarketCategoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMarketCategoriesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_market_categories, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.progressBar.visible(false)




        return binding.root
    }
}