package com.example.spasdomuserapp.ui.services.planned.categories.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.spasdomuserapp.models.Photo

class InfoPlannedOrderViewModel (
    private val state: SavedStateHandle
) : ViewModel() {

    var comment: String = ""

    private val _photos = MutableLiveData<List<Photo>>(listOf())
    val photos: MutableLiveData<List<Photo>>
        get() = _photos
}

