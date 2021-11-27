package com.example.spasdomuserapp.ui.base

import androidx.lifecycle.ViewModel
import com.example.spasdomuserapp.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {


}