package com.example.spasdomuserapp.ui.services.planned.categories.date

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentPlannedDateBinding
import com.example.spasdomuserapp.models.WorkerDay
import com.example.spasdomuserapp.ui.services.planned.categories.AddOrderViewModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


class PlannedDateFragment : Fragment(R.layout.fragment_planned_date) {

    private lateinit var selectedDate: LocalDate
    private var calendarViewAdapter: CalendarViewAdapter? = null
    private val viewModel: AddOrderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPlannedDateBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_planned_date, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        selectedDate = LocalDate.now()

        binding.btnLeft.setOnClickListener {
            selectedDate = selectedDate.minusMonths(1)
            drawMonthView(binding)
        }

        binding.btnRight.setOnClickListener {
            selectedDate = selectedDate.plusMonths(1)
            drawMonthView(binding)
        }

        drawMonthView(binding)

        return binding.root
    }


    private fun drawMonthView(binding: FragmentPlannedDateBinding) {
        calendarViewAdapter = CalendarViewAdapter(DateClick {
            Log.i("click", it.toString())
        })

        binding.root.findViewById<RecyclerView>(R.id.calendarRecyclerView).apply {
            layoutManager = GridLayoutManager(activity, 7)
            adapter = calendarViewAdapter
        }


        val dateText = monthYearFromDate(selectedDate)
        for (i in viewModel.workerMonth.indices) {
            if (viewModel.workerMonth[i].month == dateText) {
                calendarViewAdapter?.daysOfMonth = viewModel.workerMonth[i].workerMonth
            }
        }

        binding.monthYearTV.text = dateText
    }


    private fun monthYearFromDate(date: LocalDate): String? {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }
}

class DateClick(val block: (WorkerDay) -> Unit) {
    fun onClick(workerDate: WorkerDay) = block(workerDate)
}