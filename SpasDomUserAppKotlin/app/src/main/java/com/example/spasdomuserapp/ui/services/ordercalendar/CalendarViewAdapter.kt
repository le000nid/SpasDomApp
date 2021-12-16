package com.example.spasdomuserapp.ui.services.ordercalendar

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

    var currentDay: String = ""
    var currentMonth: String = ""
    var activeMonth: String = ""

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
            it.date = daysOfMonth[position]

            // validation
            if (currentMonth == activeMonth) {
                if (daysOfMonth[position].day != "" && daysOfMonth[position].day.toInt() >= currentDay.toInt()) {
                    it.click = callback
                }
            } else if (daysOfMonth[position].day != ""){
                it.click = callback
            }
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