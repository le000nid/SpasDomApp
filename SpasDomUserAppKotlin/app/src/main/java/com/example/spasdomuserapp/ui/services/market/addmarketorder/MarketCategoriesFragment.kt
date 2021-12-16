package com.example.spasdomuserapp.ui.services.market.addmarketorder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentMarketCategoriesBinding
import com.example.spasdomuserapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarketCategoriesFragment : Fragment() {

    private val viewModel: MarketCategoriesViewModel by viewModels()
    private var marketAdapter: MarketCategoriesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMarketCategoriesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_market_categories, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.progressBar.visible(false)

        // TODO(Uncomment when you will receive categories from server)
        /*viewModel.getMarketCategories()

        viewModel.marketCategories.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visible(false)
                    marketAdapter?.sectionedCategories = it.value
                }
                is Resource.Loading -> {
                    binding.progressBar.visible(true)
                }
                is Resource.Failure -> handleApiError(it) { }
            }
        }*/

        marketAdapter = MarketCategoriesAdapter(this)

        binding.marketSectionedRv.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = marketAdapter
        }

        // TODO(HARDCODE remove when REST is ready)
        marketAdapter?.sectionedCategories = viewModel.categoriesLists


        return binding.root
    }
}