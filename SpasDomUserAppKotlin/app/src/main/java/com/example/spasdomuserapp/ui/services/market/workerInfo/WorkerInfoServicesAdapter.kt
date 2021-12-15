package com.example.spasdomuserapp.ui.services.market.workerInfo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.ItemWorkerServiceBinding
import com.example.spasdomuserapp.models.WorkerService

class WorkerInfoServicesAdapter : RecyclerView.Adapter<WorkerInfoServicesAdapter.ViewHolder>() {

    var services: List<WorkerService> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val withDataBinding: ItemWorkerServiceBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ViewHolder.LAYOUT,
            parent,
            false)
        return ViewHolder(withDataBinding)
    }

    override fun getItemCount() = services.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.workerService = services[position]
        }
    }

    class ViewHolder(val viewDataBinding: ItemWorkerServiceBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_worker_service
        }
    }
}