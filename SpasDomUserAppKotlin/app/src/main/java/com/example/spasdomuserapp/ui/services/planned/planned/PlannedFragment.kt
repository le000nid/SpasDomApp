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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.DialogRateOrderBinding
import com.example.spasdomuserapp.databinding.FragmentPlannedBinding
import com.example.spasdomuserapp.domain.PlannedOrder
import com.example.spasdomuserapp.ui.services.ServicesFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlannedFragment : Fragment() {

    private val viewModel: PlannedViewModel by viewModels()

    private var activeAdapter: ActivePlanedOrdersAdapter? = null
    private var historyAdapter: HistoryPlanedOrdersAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.activePlannedOrders.observe(viewLifecycleOwner, { orders ->
            orders?.apply {
                activeAdapter?.plannedOrders = orders
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

        activeAdapter = ActivePlanedOrdersAdapter(PlannedOrderClick {
            val action = ServicesFragmentDirections.actionServicesFragmentToActivePlannedDetailedFragment(it)
            findNavController().navigate(action)
        })

        historyAdapter = HistoryPlanedOrdersAdapter(PlannedOrderClick {
            val action = ServicesFragmentDirections.actionServicesFragmentToHistoryPlannedDetailedFragment(it)
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


    private fun showCustomInputAlertDialog(plannedOrder: PlannedOrder) {
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
                viewModel.updatePlannedOrder(plannedOrder.copy(userRate = rating.toInt(), userReview = review))
                //TODO(Make post/put request to remote server)
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}

class ReviewClick(val block: (PlannedOrder) -> Unit) {
    fun onReviewClick(plannedOrder: PlannedOrder) = block(plannedOrder)
}

class PlannedOrderClick(val block: (PlannedOrder) -> Unit) {
    fun onClick(plannedOrder: PlannedOrder) = block(plannedOrder)
}
