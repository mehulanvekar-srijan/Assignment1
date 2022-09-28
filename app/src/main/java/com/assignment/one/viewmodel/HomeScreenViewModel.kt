package com.assignment.one.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.assignment.one.domain.model.Product
import com.assignment.one.domain.repository.RemoteRepository
import com.assignment.one.networking.NetworkStatus

class HomeScreenViewModel : ViewModel() {

    private val _productListSate: MutableState<List<Product>> = mutableStateOf(listOf())
    val productListSate: State<List<Product>> = _productListSate

    private val _networkStatusState: MutableState<NetworkStatus> = mutableStateOf(NetworkStatus.Fetching)
    val networkStatusState: State<NetworkStatus> = _networkStatusState

    fun execute(){
        //Pass State objects to Repo so that it can edit it whenever the response has arrived
        RemoteRepository.fetchFromServer(_productListSate,_networkStatusState)
    }
}