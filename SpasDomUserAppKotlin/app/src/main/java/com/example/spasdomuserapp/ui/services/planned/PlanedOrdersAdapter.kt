package com.example.spasdomuserapp.ui.services.planned

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.ItemPlannedOrderBinding
import com.example.spasdomuserapp.domain.PlannedOrder


class PlanedOrdersAdapter(val callback: PlannedOrderClick) : RecyclerView.Adapter<PlanedOrdersAdapter.PlannedOrdersViewHolder>() {

    /**
     * The NewsItem that our Adapter will show
     */
    var plannedOrders: List<PlannedOrder> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value

            // Notify any registered observers that the data set has changed. This will cause every
            // element in our RecyclerView to be invalidated.
            notifyDataSetChanged()
        }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlannedOrdersViewHolder {
        val withDataBinding: ItemPlannedOrderBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PlannedOrdersViewHolder.LAYOUT,
            parent,
            false)
        return PlannedOrdersViewHolder(withDataBinding)
    }

    override fun getItemCount() = plannedOrders.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: PlannedOrdersViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.plannedOrder = plannedOrders[position]
            it.orderCallback = callback
        }
    }

    /**
     * ViewHolder for news items. All work is done by data binding.
     */
    class PlannedOrdersViewHolder(val viewDataBinding: ItemPlannedOrderBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_planned_order
        }
    }
}