package com.example.spasdomworkerapp.ui.home.order.active

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spasdomworkerapp.domain.Photo

class AddOrderViewModel(): ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>(listOf())

    val photos: MutableLiveData<List<Photo>>
        get() = _photos

}