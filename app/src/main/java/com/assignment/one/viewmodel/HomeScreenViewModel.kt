package com.assignment.one.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.assignment.one.domain.model.Product
import com.assignment.one.domain.repository.remoteRepository
import com.assignment.one.utils.NetworkStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val _productListSate: MutableState<List<Product>> = mutableStateOf(listOf())
    val productListSate: State<List<Product>> = _productListSate

    private val _networkStatusState: MutableState<NetworkStatus> = mutableStateOf(NetworkStatus.Fetching)
    val networkStatusState: State<NetworkStatus> = _networkStatusState

    fun execute(scope: CoroutineScope){

        remoteRepository.fetchFromServer() //Fetch JSON data

        scope.launch {
            var sec = 5
            while(sec >= 1){
                sec--
                delay(1000)
                _productListSate.value = remoteRepository.getProductList()

                if(_productListSate.value.isEmpty()) continue

                else {
                    _networkStatusState.value = NetworkStatus.Success
                    break
                }
            }
            if(_productListSate.value.isEmpty()) _networkStatusState.value = NetworkStatus.Failed
        }
    }
}