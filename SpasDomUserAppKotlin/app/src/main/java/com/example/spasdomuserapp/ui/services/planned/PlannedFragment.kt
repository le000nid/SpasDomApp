package com.example.spasdomuserapp.ui.services.planned

import android.app.PendingIntent.getActivity
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.DialogRateOrderBinding
import com.example.spasdomuserapp.databinding.FragmentPlannedBinding
import com.example.spasdomuserapp.domain.PlannedOrder
import kotlinx.android.synthetic.main.dialog_rate_order.view.*
import timber.log.Timber
import kotlin.properties.Delegates

class PlannedFragment : Fragment() {

    private val viewModel: PlannedViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, PlannedViewModel.Factory(activity.application))[PlannedViewModel::class.java]
    }

    private var viewModelActiveAdapter: ActivePlanedOrdersAdapter? = null

    private var viewModelHistoryAdapter: HistoryPlanedOrdersAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.activePlannedOrders.observe(viewLifecycleOwner, { orders ->
            orders?.apply {
                viewModelActiveAdapter?.plannedOrders = orders
            }
        })

        viewModel.historyPlannedOrders.observe(viewLifecycleOwner, { orders ->
            orders?.apply {
                viewModelHistoryAdapter?.historyOrders = orders
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

        viewModelActiveAdapter = ActivePlanedOrdersAdapter(PlannedOrderClick {
            //TODO(navigate to decs screen)
        })

        viewModelHistoryAdapter = HistoryPlanedOrdersAdapter(PlannedOrderClick {
            //TODO(navigate to decs screen)
        }, ReviewClick {
            showCustomInputAlertDialog(it)
        })

        binding.root.findViewById<RecyclerView>(R.id.active_planned_rv).apply {
            layoutManager = object : LinearLayoutManager(context){ override fun canScrollVertically(): Boolean { return false } }
            adapter = viewModelActiveAdapter
        }

        binding.root.findViewById<RecyclerView>(R.id.history_planned_rv).apply {
            layoutManager = object : LinearLayoutManager(context){ override fun canScrollVertically(): Boolean { return false } }
            adapter = viewModelHistoryAdapter
        }


        binding.apply {
            swipeRefreshPlanned.setOnRefreshListener {
                viewModel?.swipeToRefresh()
                swipeRefreshPlanned.isRefreshing = false
            }
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
