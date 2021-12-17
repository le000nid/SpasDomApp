package com.example.spasdomuserapp.ui.services.market.addmarketorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spasdomuserapp.models.CategoriesList
import com.example.spasdomuserapp.network.Resource
import com.example.spasdomuserapp.repository.ServicesRepository
import com.example.spasdomuserapp.responses.SectionCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketCategoriesViewModel @Inject constructor(
    private val repository: ServicesRepository
) : ViewModel() {

    private val _marketCategories: MutableLiveData<Resource<List<SectionCategories>>> = MutableLiveData()
    val marketCategories: LiveData<Resource<List<SectionCategories>>>
        get() = _marketCategories

    fun getMarketCategories() = viewModelScope.launch {
        _marketCategories.value = Resource.Loading
        _marketCategories.value = repository.getMarketCategories()
    }

    val categoriesLists: List<SectionCategories> = listOf(
        SectionCategories("Популярное", listOf(
                CategoriesList("Помощь с пк", 5, ""),
                CategoriesList("Помощь с ремонтом", 5, ""),
                CategoriesList("Няня на час", 5, ""),
                CategoriesList("Помощь с уборкой", 5, ""),
                CategoriesList("Помощь с уборкой", 5, "")
            )),
        SectionCategories("Необходимое", listOf(
            CategoriesList("Помощь с пк", 5, ""),
            CategoriesList("Помощь с ремонтом", 5, ""),
            CategoriesList("Няня на час", 5, ""),
            CategoriesList("Помощь с уборкой", 5, ""),
            CategoriesList("Помощь с уборкой", 5, "")
        )),
        SectionCategories("Важное", listOf(
            CategoriesList("Помощь с пк", 5, ""),
            CategoriesList("Помощь с ремонтом", 5, ""),
            CategoriesList("Няня на час", 5, ""),
            CategoriesList("Помощь с уборкой", 5, ""),
            CategoriesList("Помощь с уборкой", 5, "")
        )),
        SectionCategories("Что-то ещё", listOf(
            CategoriesList("Помощь с пк", 5, ""),
            CategoriesList("Помощь с ремонтом", 5, ""),
            CategoriesList("Няня на час", 5, ""),
            CategoriesList("Помощь с уборкой", 5, ""),
            CategoriesList("Помощь с уборкой", 5, "")
        ))
    )

}
