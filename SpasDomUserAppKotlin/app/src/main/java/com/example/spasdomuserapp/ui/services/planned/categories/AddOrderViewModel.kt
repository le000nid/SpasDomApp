package com.example.spasdomuserapp.ui.services.planned.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.spasdomuserapp.models.PlannedCategory

class AddOrderViewModel(): ViewModel() {

    val plannedCategories: List<PlannedCategory> = listOf(
        PlannedCategory("Счетчики", 2, listOf(
            PlannedCategory("Вода", 3, null),
            PlannedCategory("Газ", 4, null),
            PlannedCategory("Плита", 5, null),
            PlannedCategory("Двор", 6, null),
            PlannedCategory("Плита", 5, null),
            PlannedCategory("Двор", 6, null)
        )),
        PlannedCategory("Вода", 3, null),
        PlannedCategory("Газ", 4, null),
        PlannedCategory("Плита", 5, null),
        PlannedCategory("Двор", 6, null)
    )

}