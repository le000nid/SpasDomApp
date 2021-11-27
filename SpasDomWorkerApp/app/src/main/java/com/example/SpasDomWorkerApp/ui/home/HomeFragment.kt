package com.example.spasdomworkerapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomworkerapp.R
import com.example.spasdomworkerapp.databinding.FragmentHomeBinding
import com.example.spasdomworkerapp.domain.Order
import java.text.SimpleDateFormat
import java.util.*

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

    private var viewModelOrdersAdapter: OrderItemsAdapter? = null

    /**
     * Called immediately after onCreateView() has returned, and fragment's
     * view hierarchy has been created.  It can be used to do final
     * initialization once these pieces are in place, such as retrieving
     * views or restoring state.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.orderItems.observe(viewLifecycleOwner, { orders ->
            orders?.apply {
                viewModelOrdersAdapter?.orderItems = orders
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

        viewModelOrdersAdapter = OrderItemsAdapter(OrderItemClick {
            // context is not around, we can safely discard this click since the Fragment is no
            // longer on the screen
//            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewsDetailedFragment(it))
        })

        binding.root.findViewById<TextView>(R.id.date).setText(viewModel.OrderGetFormat)

        binding.root.findViewById<RecyclerView>(R.id.orders_rv).apply {

            /** TODO(BUG. Fix someday or forget)
             * This disables scrolling, but for some unknown reason only displays the first three elements.
             * However, that's what we need, so this bug has become a feature :)
             */
            layoutManager =
                object : LinearLayoutManager(context){ override fun canScrollVertically(): Boolean { return false } }
            adapter = viewModelOrdersAdapter
        }

        binding.apply {
            swipeRefreshHome.setOnRefreshListener {
                viewModel?.swipeToRefresh()
                swipeRefreshHome.isRefreshing = false
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }
}

/**
 * Click listener for Orders. By giving the block a name it helps a reader understand what it does.
 */
class OrderItemClick(val block: (Order) -> Unit) {
    /**
     * Called when a news is clicked
     *
     * @param newsItem the newsItem that was clicked
     */
    fun onClick(order: Order) = block(order)
}



