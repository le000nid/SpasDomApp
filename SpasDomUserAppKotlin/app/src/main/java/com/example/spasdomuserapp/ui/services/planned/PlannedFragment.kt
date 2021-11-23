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
import com.example.spasdomuserapp.domain.ActivePlannedOrder

class PlannedFragment : Fragment() {

    private val viewModel: PlannedViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, PlannedViewModel.Factory(activity.application))[PlannedViewModel::class.java]
    }

    private var viewModelAdapter: ActivePlannedOrdersAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.activePlannedOrders.observe(viewLifecycleOwner, { orders ->
            orders?.apply {
                viewModelAdapter?.activePlannedOrders = orders
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

        viewModelAdapter = ActivePlannedOrdersAdapter(ActivePlannedOrderClick {
            //TODO(navigate to decs screen)
        })

        binding.root.findViewById<RecyclerView>(R.id.active_planned_rv).apply {
            layoutManager = object : LinearLayoutManager(context){ override fun canScrollVertically(): Boolean { return false } }
            adapter = viewModelAdapter
        }

        return binding.root
    }
}

class ActivePlannedOrderClick(val block: (ActivePlannedOrder) -> Unit) {
    fun onClick(activePlannedOrder: ActivePlannedOrder) = block(activePlannedOrder)
}