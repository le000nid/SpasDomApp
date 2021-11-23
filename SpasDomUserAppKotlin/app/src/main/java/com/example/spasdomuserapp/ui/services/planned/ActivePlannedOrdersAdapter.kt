package com.example.spasdomuserapp.ui.services.planned

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.ItemActiveOrderBinding
import com.example.spasdomuserapp.databinding.ItemNewsBinding
import com.example.spasdomuserapp.domain.ActivePlannedOrder


class ActivePlannedOrdersAdapter(val callback: ActivePlannedOrderClick) : RecyclerView.Adapter<ActivePlannedOrdersAdapter.ActivePlannedOrdersViewHolder>() {

    /**
     * The NewsItem that our Adapter will show
     */
    var activePlannedOrders: List<ActivePlannedOrder> = emptyList()
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivePlannedOrdersViewHolder {
        val withDataBinding: ItemActiveOrderBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ActivePlannedOrdersViewHolder.LAYOUT,
            parent,
            false)
        return ActivePlannedOrdersViewHolder(withDataBinding)
    }

    override fun getItemCount() = activePlannedOrders.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: ActivePlannedOrdersViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.activeOrder = activePlannedOrders[position]
            it.activeOrderCallback = callback
        }
    }

    /**
     * ViewHolder for news items. All work is done by data binding.
     */
    class ActivePlannedOrdersViewHolder(val viewDataBinding: ItemActiveOrderBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_active_order
        }
    }
}