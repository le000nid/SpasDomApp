/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.spasdomuserapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spasdomuserapp.database.CacheDao
import com.example.spasdomuserapp.repository.NewsItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * HomeViewModel designed to store and manage UI-related data in a lifecycle conscious way. This
 * allows data to survive configuration changes such as screen rotations. In addition, background
 * work such as fetching network results can continue through configuration changes and deliver
 * results after the new Fragment or Activity is available.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cacheDao: CacheDao,
): ViewModel() {

    private val videosRepository = NewsItemsRepository(cacheDao)

    init {
        viewModelScope.launch {
            videosRepository.refreshNewsItems()
            videosRepository.refreshAlerts()
        }
    }

    val newsItems = videosRepository.newsItems

    val alerts = videosRepository.alerts

    fun swipeToRefresh() = viewModelScope.launch {
        videosRepository.refreshNewsItems()
        videosRepository.refreshAlerts()
    }
}
