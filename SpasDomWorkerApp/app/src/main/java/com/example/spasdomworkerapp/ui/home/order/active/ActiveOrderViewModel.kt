package com.example.spasdomworkerapp.ui.home.order.active

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spasdomworkerapp.models.Photo

class AddOrderViewModel(): ViewModel() {

    private val _completePhotos = MutableLiveData<List<Photo>>(listOf())

    private val _doorPhotos = MutableLiveData<List<Photo>>(listOf())

    val completePhotos: MutableLiveData<List<Photo>>
        get() = _completePhotos

    val doorPhotos: MutableLiveData<List<Photo>>
        get() = _doorPhotos

}