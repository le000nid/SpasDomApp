package com.example.spasdomuserapp.ui.services.planned.categories.date

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentPlannedDateBinding
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


class PlannedDateFragment : Fragment(R.layout.fragment_planned_date) {

    private lateinit var selectedDate: LocalDate
    private var calendarViewAdapter: CalendarViewAdapter? = null


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
            Log.i("click", it)
        })

        binding.root.findViewById<RecyclerView>(R.id.calendarRecyclerView).apply {
            layoutManager = GridLayoutManager(activity, 7)
            adapter = calendarViewAdapter
        }

        val daysInMonth: List<String> = daysInMonthList(selectedDate)
        calendarViewAdapter?.daysOfMonth = daysInMonth


        binding.monthYearTV.text = monthYearFromDate(selectedDate)
    }


    private fun monthYearFromDate(date: LocalDate): String? {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }


    private fun daysInMonthList(selectedDate: LocalDate): List<String> {

        val daysInMonthArray: MutableList<String> = mutableListOf()
        val yearMonth: YearMonth = YearMonth.from(selectedDate)

        val daysInMonth: Int = yearMonth.lengthOfMonth()

        val firstOfMonth: LocalDate = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value

        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray.toList()
    }
}

class DateClick(val block: (String) -> Unit) {
    fun onClick(workerDate: String) = block(workerDate)
}