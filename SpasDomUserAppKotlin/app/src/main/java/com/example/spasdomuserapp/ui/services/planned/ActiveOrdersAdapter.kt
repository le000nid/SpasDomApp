package com.example.spasdomuserapp.ui.services.planned

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.ItemOrderBinding
import com.example.spasdomuserapp.models.Order


class ActiveOrdersAdapter(val callback: OrderClick) : RecyclerView.Adapter<ActiveOrdersAdapter.ActivePlannedOrdersViewHolder>() {

    /**
     * The NewsItem that our Adapter will show
     */
    var activeOrders: List<Order> = emptyList()
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
        val withDataBinding: ItemOrderBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ActivePlannedOrdersViewHolder.LAYOUT,
            parent,
            false)
        return ActivePlannedOrdersViewHolder(withDataBinding)
    }

    override fun getItemCount() = activeOrders.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: ActivePlannedOrdersViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.order = activeOrders[position]
            it.orderClick = callback
        }
    }

    /**
     * ViewHolder for news items. All work is done by data binding.
     */
    class ActivePlannedOrdersViewHolder(val viewDataBinding: ItemOrderBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_order
        }
    }
}