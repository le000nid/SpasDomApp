package com.example.spasdomuserapp.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentHomeBinding
import com.example.spasdomuserapp.domain.NewsItem
import com.example.spasdomuserapp.firebase.Events
import timber.log.Timber

/**
 * Show a list of newsItems on screen.
 */
class HomeFragment : Fragment() {

    /**
     * One way to delay creation of the viewModel until an appropriate lifecycle method is to use
     * lazy. This requires that viewModel not be referenced before onViewCreated(), which we
     * do in this Fragment.
     */
    private val viewModel: HomeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, HomeViewModel.Factory(activity.application))[HomeViewModel::class.java]
    }

    /**
     * RecyclerView Adapter for converting a list of Video to cards.
     */
    private var viewModelAdapter: NewsItemsAdapter? = null

    private var viewModelAlertsAdapter: AlertsAdapter? = null

    /**
     * Called immediately after onCreateView() has returned, and fragment's
     * view hierarchy has been created.  It can be used to do final
     * initialization once these pieces are in place, such as retrieving
     * views or restoring state.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.newsItems.observe(viewLifecycleOwner, { news ->
            news?.apply {
                viewModelAdapter?.newsItems = news
            }
        })

        Events.serviceEvent.observe(viewLifecycleOwner, { item ->
            // TODO(I think we don't need this method because we'll get notification while opening the app from GET)
            Log.i("fragment", item.toString())
        })

        viewModel.alerts.observe(viewLifecycleOwner, { alerts ->
            alerts?.apply {
                Log.i("alerts", alerts.toString())
                viewModelAlertsAdapter?.alerts = alerts
            }
        })
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container,false)

        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        viewModelAdapter = NewsItemsAdapter(NewsItemClick {
            // context is not around, we can safely discard this click since the Fragment is no
            // longer on the screen
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewsDetailedFragment(it))
        })

        viewModelAlertsAdapter = AlertsAdapter()

        binding.root.findViewById<RecyclerView>(R.id.news_recycler_view).apply {

            /*val myLinearLayoutManager = object : LinearLayoutManager(context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }*/

            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        binding.root.findViewById<RecyclerView>(R.id.alerts_rv).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAlertsAdapter
        }

        binding.apply {
            swipeRefreshHome.setOnRefreshListener {
                viewModel?.swipeToRefresh()
                swipeRefreshHome.isRefreshing = false
            }
        }

        return binding.root
    }
}

/**
 * Click listener for News. By giving the block a name it helps a reader understand what it does.
 */
class NewsItemClick(val block: (NewsItem) -> Unit) {
    /**
     * Called when a news is clicked
     *
     * @param newsItem the newsItem that was clicked
     */
    fun onClick(newsItem: NewsItem) = block(newsItem)
}
