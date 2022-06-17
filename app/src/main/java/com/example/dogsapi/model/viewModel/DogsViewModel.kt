package com.example.dogsapi.model.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.dogsapi.model.Dogs
import com.example.dogsapi.model.network.ApiService
import com.example.dogsapi.model.pager.PagingDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(val apiService: ApiService):ViewModel() {

    val getDogs:Flow<PagingData<Dogs>>
    =Pager(config = PagingConfig(20, enablePlaceholders = false)) {
         PagingDataSource(apiService)
    }.flow.cachedIn(viewModelScope)
}