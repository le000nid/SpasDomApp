package com.example.spasdomuserapp.ui.services.planned

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentPlannedBinding
import com.example.spasdomuserapp.domain.PlannedOrder

class PlannedFragment : Fragment() {

    private val viewModel: PlannedViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, PlannedViewModel.Factory(activity.application))[PlannedViewModel::class.java]
    }

    private var viewModelAdapter: PlanedOrdersAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.activePlannedOrders.observe(viewLifecycleOwner, { orders ->
            orders?.apply {
                viewModelAdapter?.plannedOrders = orders
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

        viewModelAdapter = PlanedOrdersAdapter(PlannedOrderClick {
            //TODO(navigate to decs screen)
        })

        binding.root.findViewById<RecyclerView>(R.id.active_planned_rv).apply {
            layoutManager = object : LinearLayoutManager(context){ override fun canScrollVertically(): Boolean { return false } }
            adapter = viewModelAdapter
        }

        binding.apply {
            swipeRefreshPlanned.setOnRefreshListener {
                viewModel?.swipeToRefresh()
                swipeRefreshPlanned.isRefreshing = false
            }
        }


        return binding.root
    }
}

class PlannedOrderClick(val block: (PlannedOrder) -> Unit) {
    fun onClick(plannedOrder: PlannedOrder) = block(plannedOrder)
}