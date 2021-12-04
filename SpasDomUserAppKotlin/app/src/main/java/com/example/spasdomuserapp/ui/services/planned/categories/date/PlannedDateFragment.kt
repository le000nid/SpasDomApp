package com.example.spasdomuserapp.ui.services.planned.categories.date

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentPlannedCategoriesBinding
import com.example.spasdomuserapp.databinding.FragmentPlannedDateBinding
import com.example.spasdomuserapp.models.WorkerDate
import com.example.spasdomuserapp.ui.services.planned.categories.lvl1.PlannedCategoriesAdapter
import com.example.spasdomuserapp.ui.services.planned.categories.lvl1.PlannedCategoriesClick
import com.example.spasdomuserapp.ui.services.planned.categories.lvl1.PlannedCategoriesFragmentDirections
import java.util.Date
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth


class PlannedDateFragment : Fragment(R.layout.fragment_planned_date) {

    private lateinit var selectedDate: LocalDate
    private var calendarViewAdapter: CalendarViewAdapter? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPlannedDateBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_planned_date, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val formatterText = SimpleDateFormat("MMMM yyyy")
        val currentDateText: String = formatterText.format(Date())
        binding.monthYearTV.text = currentDateText

        calendarViewAdapter = CalendarViewAdapter(DateClick {
            Log.i("click", it)
        })

        binding.root.findViewById<RecyclerView>(R.id.calendarRecyclerView).apply {
            layoutManager = GridLayoutManager(activity, 7)
            adapter = calendarViewAdapter
        }

        binding.btnLeft.setOnClickListener {
            selectedDate = selectedDate.minusMonths(1)
            //
        }

        binding.btnRight.setOnClickListener {
            selectedDate = selectedDate.plusMonths(1)
            //
        }

        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedDate = LocalDate.now()

        val daysInMonth: List<String> = daysInMonthList(selectedDate)
        calendarViewAdapter?.daysOfMonth = daysInMonth
    }


    @RequiresApi(Build.VERSION_CODES.O)
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