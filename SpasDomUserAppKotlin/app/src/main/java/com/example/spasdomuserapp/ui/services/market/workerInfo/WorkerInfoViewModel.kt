package com.example.spasdomuserapp.ui.services.market.workerInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spasdomuserapp.models.Worker
import com.example.spasdomuserapp.models.WorkerReview
import com.example.spasdomuserapp.models.WorkerService
import com.example.spasdomuserapp.network.Resource
import com.example.spasdomuserapp.repository.ServicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WorkerInfoViewModel @Inject constructor(
    private val repository: ServicesRepository
) : ViewModel() {

    private val _marketWorker: MutableLiveData<Resource<Worker>> = MutableLiveData()
    val marketWorker: LiveData<Resource<Worker>>
        get() = _marketWorker

    fun getMarketWorker() = viewModelScope.launch {
        _marketWorker.value = Resource.Loading
        _marketWorker.value = repository.getMarketWorker()
    }

    private val workerService: List<WorkerService> = listOf(
        WorkerService("Уборка", "1500 руб"),
        WorkerService("Готовка", "500 руб"),
        WorkerService("Стирка", "2500 руб"),
    )

    private val workerReviews: List<WorkerReview> = listOf(
        WorkerReview("Александр", "Белов", "12.11.21", 5, "", "Исполнитель прекрасно справился с заказом"),
        WorkerReview("Екатерина", "Овалова", "12.11.21", 5, "", "Исполнитель прекрасно справился с заказом"),
        WorkerReview("Михаил", "Мезцов", "12.11.21", 5, "", "Исполнитель прекрасно справился с заказом"),
    )

    val worker = Worker(1, "Николай", "Васильев", "", 4, "The best worker of the year", "4 года", "", workerService, workerReviews)

}