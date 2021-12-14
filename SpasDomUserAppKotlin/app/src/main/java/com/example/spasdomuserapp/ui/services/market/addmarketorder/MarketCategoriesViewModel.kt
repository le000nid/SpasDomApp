package com.example.spasdomuserapp.ui.services.market.addmarketorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spasdomuserapp.models.SectionCategory
import com.example.spasdomuserapp.network.Resource
import com.example.spasdomuserapp.repository.ServicesRepository
import com.example.spasdomuserapp.responses.SectionCategories
import com.example.spasdomuserapp.responses.SectionCategoriesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketCategoriesViewModel @Inject constructor(
    private val repository: ServicesRepository
) : ViewModel() {

    private val _marketCategories: MutableLiveData<Resource<SectionCategoriesResponse>> = MutableLiveData()
    val marketCategories: LiveData<Resource<SectionCategoriesResponse>>
        get() = _marketCategories

    fun getMarketCategories() = viewModelScope.launch {
        _marketCategories.value = Resource.Loading
        _marketCategories.value = repository.getMarketCategories()
    }

    val categoriesLists: List<SectionCategories> = listOf(
        SectionCategories("Популярное", listOf(
                SectionCategory("Помощь с пк", 5, ""),
                SectionCategory("Помощь с ремонтом", 5, ""),
                SectionCategory("Няня на час", 5, ""),
                SectionCategory("Помощь с уборкой", 5, ""),
                SectionCategory("Помощь с уборкой", 5, "")
            )),
        SectionCategories("Необходимое", listOf(
            SectionCategory("Помощь с пк", 5, ""),
            SectionCategory("Помощь с ремонтом", 5, ""),
            SectionCategory("Няня на час", 5, ""),
            SectionCategory("Помощь с уборкой", 5, ""),
            SectionCategory("Помощь с уборкой", 5, "")
        )),
        SectionCategories("Важное", listOf(
            SectionCategory("Помощь с пк", 5, ""),
            SectionCategory("Помощь с ремонтом", 5, ""),
            SectionCategory("Няня на час", 5, ""),
            SectionCategory("Помощь с уборкой", 5, ""),
            SectionCategory("Помощь с уборкой", 5, "")
        ))
    )

}
