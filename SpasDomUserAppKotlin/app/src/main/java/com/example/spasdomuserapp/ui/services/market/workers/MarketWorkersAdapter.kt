package com.example.spasdomuserapp.ui.services.market.workers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.ItemPreviewWorkerBinding
import com.example.spasdomuserapp.models.WorkerPreview

class MarketWorkersAdapter(val callback: WorkerClick) : RecyclerView.Adapter<MarketWorkersAdapter.ViewHolder>() {

    var workers: List<WorkerPreview> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val withDataBinding: ItemPreviewWorkerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ViewHolder.LAYOUT,
            parent,
            false)
        return ViewHolder(withDataBinding)
    }

    override fun getItemCount() = workers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.worker = workers[position]
            it.workerClick = callback
        }
    }

    class ViewHolder(val viewDataBinding: ItemPreviewWorkerBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_preview_worker
        }
    }
}