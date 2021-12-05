package com.example.spasdomuserapp.ui.services.planned.categories.date

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.CalendarDefaultViewBinding
import com.example.spasdomuserapp.models.WorkerDay

class CalendarViewAdapter(val callback: DateClick) : RecyclerView.Adapter<CalendarViewAdapter.CalendarViewHolder>() {

    var daysOfMonth: List<WorkerDay> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val withDataBinding: CalendarDefaultViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CalendarViewHolder.LAYOUT,
            parent,
            false)
        return CalendarViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.viewDataBinding.also {
            //TODO(disable previous days)
            it.date = daysOfMonth[position]
            it.click = callback
        }
    }

    override fun getItemCount() = daysOfMonth.size


    class CalendarViewHolder(val viewDataBinding: CalendarDefaultViewBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.calendar_default_view
        }
    }
}