package com.example.spasdomuserapp.ui.services.market.workerInfo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.ItemWorkerReviewBinding
import com.example.spasdomuserapp.models.WorkerReview


class WorkerInfoReviewsAdapter : RecyclerView.Adapter<WorkerInfoReviewsAdapter.ViewHolder>() {

    var reviews: List<WorkerReview> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val withDataBinding: ItemWorkerReviewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ViewHolder.LAYOUT,
            parent,
            false)
        return ViewHolder(withDataBinding)
    }

    override fun getItemCount() = reviews.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.workerReview = reviews[position]
        }
    }

    class ViewHolder(val viewDataBinding: ItemWorkerReviewBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_worker_review
        }
    }
}