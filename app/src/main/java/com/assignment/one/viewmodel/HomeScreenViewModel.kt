package com.assignment.one.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.one.domain.model.Product
import com.assignment.one.domain.repository.RemoteRepository
import com.assignment.one.networking.NetworkStatus
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class HomeScreenViewModel : ViewModel() {

    private val _productListSate: MutableState<List<Product>> = mutableStateOf(listOf())
    val productListSate: State<List<Product>> = _productListSate

    private val _networkStatusState: MutableState<NetworkStatus> = mutableStateOf(NetworkStatus.Fetching)
    val networkStatusState: State<NetworkStatus> = _networkStatusState

    suspend fun execute(){

        val deferred : Deferred<List<Product>> = viewModelScope.async(Dispatchers.IO) {
            RemoteRepository.fetchFromServer()
        }

        _productListSate.value = deferred.await()
        _networkStatusState.value = NetworkStatus.Success
    }
}