package com.example.spasdomuserapp.ui.services.planned.categories.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.spasdomuserapp.models.PlannedCategory

class Lvl1PlannedOrderViewModel(
    private val state: SavedStateHandle
) : ViewModel() {

    val plannedCategories: List<PlannedCategory> = listOf(
        PlannedCategory("Счетчики", 1, listOf(
            PlannedCategory("Вода", 11, null),
            PlannedCategory("Газ", 12,null),
            PlannedCategory("Плита", 13, null),
            PlannedCategory("Двор", 14, null),
            PlannedCategory("Плита", 15, null),
            PlannedCategory("Двор", 16, null)
        )),
        PlannedCategory("Вода", 2, null),
        PlannedCategory("Газ", 3, null),
        PlannedCategory("Плита", 4, null),
        PlannedCategory("Двор", 5, null)
    )
}