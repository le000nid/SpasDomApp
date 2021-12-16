package com.example.spasdomuserapp.ui.services.planned.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spasdomuserapp.models.CategoriesList
import com.example.spasdomuserapp.network.Resource
import com.example.spasdomuserapp.repository.ServicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesPlannedViewModel @Inject constructor(
    private val repository: ServicesRepository
) : ViewModel() {

    private val _plannedCategories: MutableLiveData<Resource<List<CategoriesList>>> = MutableLiveData()
    val plannedCategories: LiveData<Resource<List<CategoriesList>>>
        get() = _plannedCategories

    fun getPlannedCategories() = viewModelScope.launch {
        _plannedCategories.value = Resource.Loading
        _plannedCategories.value = repository.getPlannedCategories()
    }

    val categoriesLists: List<CategoriesList> = listOf(
        CategoriesList("Счетчики", 1, "", listOf(
            CategoriesList("Вода", 11, "", null),
            CategoriesList("Газ", 12, "",null),
            CategoriesList("Плита", 13, "", null),
            CategoriesList("Двор", 14, "", null),
            CategoriesList("Плита", 15, "", null),
            CategoriesList("Двор", 16, "", null)
        )),
        CategoriesList("Вода", 2, "", null),
        CategoriesList("Газ", 3, "", null),
        CategoriesList("Плита", 4, "", null),
        CategoriesList("Двор", 5, "", null)
    )
}