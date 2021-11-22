package com.example.spasdomuserapp.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.ItemNewsBinding
import com.example.spasdomuserapp.databinding.ItemNotificationBinding
import com.example.spasdomuserapp.domain.Alert

/**
 * RecyclerView Adapter for setting up data binding on the items in the list.
 */
class AlertsAdapter : RecyclerView.Adapter<AlertsAdapter.AlertsViewHolder>() {

    /**
     * The NewsItem that our Adapter will show
     */
    var alerts: List<Alert> = emptyList()
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertsViewHolder {
        val withDataBinding: ItemNotificationBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlertsViewHolder.LAYOUT,
            parent,
            false)
        return AlertsViewHolder(withDataBinding)
    }

    override fun getItemCount() = alerts.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: AlertsViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.alert = alerts[position]
            Log.i("alertsit", it.alert.toString())
        }
    }

     /**
     * ViewHolder for news items. All work is done by data binding.
     */
    class AlertsViewHolder(val viewDataBinding: ItemNotificationBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_notification
        }
    }
}