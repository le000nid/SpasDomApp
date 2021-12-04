package com.example.spasdomuserapp.ui.services.planned.categories.date

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentPlannedDateBinding
import com.example.spasdomuserapp.models.WorkerDay
import com.example.spasdomuserapp.ui.services.planned.categories.AddOrderViewModel
import kotlinx.android.synthetic.main.fragment_planned_date.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class PlannedDateFragment : Fragment(R.layout.fragment_planned_date) {

    private lateinit var selectedDate: LocalDate
    private var calendarViewAdapter: CalendarViewAdapter? = null
    private val viewModel: AddOrderViewModel by viewModels()
    private lateinit var binding: FragmentPlannedDateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_planned_date, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        // TODO(In some reason doesn't work on devices <= O)
        selectedDate = LocalDate.now()

        binding.btnLeft.setOnClickListener {
            selectedDate = selectedDate.minusMonths(1)
            drawMonthCalendar()
        }

        binding.btnRight.setOnClickListener {
            selectedDate = selectedDate.plusMonths(1)
            drawMonthCalendar()
        }

        drawMonthCalendar()


        return binding.root
    }

    @SuppressLint("ResourceType")
    private fun drawTimeSchedule(timeList: List<String>) {

        val layoutParamsVar = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.radioGroupTimes.clearCheck()
        binding.radioGroupTimes.removeAllViews()

        for (i in timeList.indices) {
            val radioButton = RadioButton(requireContext())
            radioButton.layoutParams = layoutParamsVar
            radioButton.text = timeList[i]
            radioButton.id = i
            binding.radioGroupTimes.addView(radioButton)
        }

        radioGroupTimes.setOnCheckedChangeListener { group, checkedId ->

        }
    }

    private fun drawMonthCalendar() {
        calendarViewAdapter = CalendarViewAdapter(DateClick {

            val dateText = monthYearFromDate(selectedDate)
            val textForView = it.day + " " + dateText
            binding.textViewDate.text = textForView

            if (it.timesList != null) {
                drawTimeSchedule(it.timesList)
            } else {
                //TODO(make snackbar)
            }

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