package com.example.spasdomworkerapp.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomworkerapp.R
import com.example.spasdomworkerapp.databinding.ItemOrderBinding
import com.example.spasdomworkerapp.models.Order

class OrderItemsAdapter(val callback: OrderItemClick) : RecyclerView.Adapter<OrderItemsAdapter.OrderItemsViewHolder>() {

    var orderItems: List<Order> = emptyList()
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemsViewHolder {
        val withDataBinding: ItemOrderBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            OrderItemsViewHolder.LAYOUT,
            parent,
            false)
        return OrderItemsViewHolder(withDataBinding)
    }

    override fun getItemCount() = orderItems.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */

    override fun onBindViewHolder(holder: OrderItemsViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.orderItem = orderItems[position]
            it.orderItemCallback = callback
            if(it.orderItem?.active == true){
                it.orderCard.strokeColor = Color.parseColor("#5C6BC0")
            } else {
                it.orderCard.strokeColor = Color.parseColor("#BCBCBB")
            }
        }
    }

    /**
     * ViewHolder for news items. All work is done by data binding.
     */

    class OrderItemsViewHolder(val viewDataBinding: ItemOrderBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_order
        }
    }
}