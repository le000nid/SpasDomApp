package com.example.spasdomuserapp.ui.services.planned.planned

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.DialogRateOrderBinding
import com.example.spasdomuserapp.databinding.FragmentPlannedBinding
import com.example.spasdomuserapp.models.Order
import com.example.spasdomuserapp.network.Resource
import com.example.spasdomuserapp.ui.services.ServicesFragmentDirections
import com.example.spasdomuserapp.ui.services.base.ActiveOrdersAdapter
import com.example.spasdomuserapp.ui.services.base.HistoryOrdersAdapter
import com.example.spasdomuserapp.ui.services.base.OrderClick
import com.example.spasdomuserapp.ui.services.base.ReviewClick
import com.example.spasdomuserapp.util.handleApiError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlannedFragment : Fragment() {

    private val viewModel: PlannedViewModel by viewModels()

    private var activeAdapter: ActiveOrdersAdapter? = null
    private var historyAdapter: HistoryOrdersAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.activePlannedOrders.observe(viewLifecycleOwner, { orders ->
            orders?.apply {
                activeAdapter?.activeOrders = orders
            }
        })

        viewModel.historyPlannedOrders.observe(viewLifecycleOwner, { orders ->
            orders?.apply {
                historyAdapter?.historyOrders = orders
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPlannedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_planned, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

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

        binding.root.findViewById<RecyclerView>(R.id.active_planned_rv).apply {
            layoutManager = object : LinearLayoutManager(context){ override fun canScrollVertically(): Boolean { return false } }
            adapter = activeAdapter
        }

        binding.root.findViewById<RecyclerView>(R.id.history_planned_rv).apply {
            layoutManager = object : LinearLayoutManager(context){ override fun canScrollVertically(): Boolean { return false } }
            adapter = historyAdapter
        }


        binding.apply {
            swipeRefreshPlanned.setOnRefreshListener {
                viewModel?.swipeToRefresh()
                swipeRefreshPlanned.isRefreshing = false
            }
        }

        binding.actionAddOrder.setOnClickListener {
            val action = ServicesFragmentDirections.actionServicesFragmentToPlannedCategoriesFragment()
            findNavController().navigate(action)
        }


        return binding.root
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

                viewModel.putPlannedOrder(newOrder)

                viewModel.plannedPutResponse.observe(viewLifecycleOwner) {
                    // set up progress bar
                    when (it) {
                        is Resource.Success -> {
                            lifecycleScope.launch {
                                //viewModel.updatePlannedOrder(newOrder)
                                // TODO(Wait for response and update UI or put in in cache without refresh)
                                viewModel.swipeToRefresh()
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
