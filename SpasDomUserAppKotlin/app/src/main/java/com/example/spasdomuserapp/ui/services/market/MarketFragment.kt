package com.example.spasdomuserapp.ui.services.market

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.DialogRateOrderBinding
import com.example.spasdomuserapp.databinding.FragmentMarketBinding
import com.example.spasdomuserapp.models.Order
import com.example.spasdomuserapp.network.Resource
import com.example.spasdomuserapp.ui.services.ServicesFragmentDirections
import com.example.spasdomuserapp.ui.services.base.OrderClick
import com.example.spasdomuserapp.ui.services.base.ReviewClick
import com.example.spasdomuserapp.ui.services.base.ActiveOrdersAdapter
import com.example.spasdomuserapp.ui.services.base.HistoryOrdersAdapter
import com.example.spasdomuserapp.util.handleApiError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MarketFragment : Fragment() {

    private val viewModel: MarketViewModel by viewModels()
    private var activeAdapter: ActiveOrdersAdapter? = null
    private var historyAdapter: HistoryOrdersAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMarketBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_market, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        /* TODO(Uncomment when you will receive preview workers from server)
        viewModel.getMarketOrders()

        viewModel.marketOrders.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    viewModel.insertAllMarketOrdersToCache(it.value)
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        }*/


        activeAdapter = ActiveOrdersAdapter(OrderClick {
            val action = ServicesFragmentDirections.actionServicesFragmentToOrderDetailedFragment(it.title, it)
            findNavController().navigate(action)
        })

        historyAdapter = HistoryOrdersAdapter(OrderClick {
            val action = ServicesFragmentDirections.actionServicesFragmentToOrderDetailedFragment(it.title, it)
            findNavController().navigate(action)
        }, ReviewClick {
            showCustomInputAlertDialog(it)
        })

        binding.root.findViewById<RecyclerView>(R.id.active_market_rv).apply {
            layoutManager = object : LinearLayoutManager(context){ override fun canScrollVertically(): Boolean { return false } }
            adapter = activeAdapter
        }

        binding.root.findViewById<RecyclerView>(R.id.history_market_rv).apply {
            layoutManager = object : LinearLayoutManager(context){ override fun canScrollVertically(): Boolean { return false } }
            adapter = historyAdapter
        }


        binding.apply {
            swipeRefresh.setOnRefreshListener {
                viewModel?.getMarketOrders()
                swipeRefresh.isRefreshing = false
            }
        }

        binding.actionAddOrder.setOnClickListener {
            val action = ServicesFragmentDirections.actionServicesFragmentToMarketCategoriesFragment()
            findNavController().navigate(action)
        }


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.activeMarketOrders.observe(viewLifecycleOwner, { orders ->
            orders?.apply {
                activeAdapter?.activeOrders = orders
            }
        })

        viewModel.historyMarketOrders.observe(viewLifecycleOwner, { orders ->
            orders?.apply {
                historyAdapter?.historyOrders = orders
            }
        })
    }


    private fun showCustomInputAlertDialog(order: Order) {
        val dialogBinding = DialogRateOrderBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Оставьте отзыв о заказе")
            .setView(dialogBinding.root)
            .setPositiveButton("Отправить", null)
            .create()
        dialog.setOnShowListener {

            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val rating = dialogBinding.ratingBar.rating
                val review = dialogBinding.editTextReview.text.toString()
                val newOrder = order.copy(userRate = rating.toInt(), userReview = review)

                viewModel.putMarketOrder(newOrder)

                viewModel.marketPutResponse.observe(viewLifecycleOwner) {
                    // set up progress bar
                    when (it) {
                        is Resource.Success -> {
                            lifecycleScope.launch {
                                //viewModel.getMarketOrders()
                                dialog.dismiss()
                            }
                        }
                        is Resource.Failure -> handleApiError(it) {  } //TODO(What to do?)
                    }
                }
            }
        }
        dialog.show()
    }
}


