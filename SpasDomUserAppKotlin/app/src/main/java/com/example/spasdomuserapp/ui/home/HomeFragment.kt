package com.example.spasdomuserapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentHomeBinding
import com.example.spasdomuserapp.models.NewsItem
import com.example.spasdomuserapp.network.Resource
import com.example.spasdomuserapp.util.handleApiError
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var newsAdapter: NewsItemsAdapter? = null
    private var alertsAdapter: AlertsAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container,false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        // TODO(Uncomment when you will receive preview workers from server)
        /*viewModel.getNews()

        viewModel.news.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    viewModel.insertAllNewsToCache(it.value)
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        }*/

        // TODO(Uncomment when you will receive preview workers from server)
        /*viewModel.getAlerts()

        viewModel.alerts.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    viewModel.insertAllAlertsToCache(it.value)
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        }*/


        newsAdapter = NewsItemsAdapter(NewsItemClick {
            val action = HomeFragmentDirections.actionHomeFragmentToNewsDetailedFragment(it)
            findNavController().navigate(action)
        })

        alertsAdapter = AlertsAdapter()

        binding.root.findViewById<RecyclerView>(R.id.news_recycler_view).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                     //object : LinearLayoutManager(context, HORIZONTAL, false){ override fun canScrollVertically(): Boolean { return false } }
            adapter = newsAdapter
        }

        binding.root.findViewById<RecyclerView>(R.id.alerts_rv).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = alertsAdapter
        }

        binding.apply {
            swipeRefreshHome.setOnRefreshListener {
                viewModel?.getAlerts()
                viewModel?.getNews()
                swipeRefreshHome.isRefreshing = false
            }
        }

        binding.card2.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToServicesFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.newsItems.observe(viewLifecycleOwner, { news ->
            news?.apply {
                newsAdapter?.newsItems = news
            }
        })

        viewModel.alertsItems.observe(viewLifecycleOwner, { alerts ->
            alerts?.apply {
                alertsAdapter?.alerts = alerts
            }
        })
    }
}

class NewsItemClick(val block: (NewsItem) -> Unit) {
    fun onClick(newsItem: NewsItem) = block(newsItem)
}
