package com.example.spasdomuserapp.ui.services.planned.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.models.Photo
import com.example.spasdomuserapp.models.PlannedCategory

class AddOrderViewModel(): ViewModel() {

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

    private val _photos = MutableLiveData<MutableList<Photo>>(mutableListOf(
        Photo("1"),
        Photo("2"),
        Photo("3"),
        Photo("4"),
        Photo("5"),
        Photo("6")
    ))

    val photos: LiveData<MutableList<Photo>>
        get() = _photos
}