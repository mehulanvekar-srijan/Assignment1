package com.assignment.one.domain.repository

import androidx.compose.runtime.MutableState
import com.assignment.one.domain.model.Product
import com.assignment.one.networking.HttpClient
import com.assignment.one.networking.NetworkStatus

/*
* RemoteRepository stores data fetched from API
* other classes/functions should access this object inorder to access productList
*/
object RemoteRepository {

    //Job is to only make a call to Retrofit client
    fun fetchFromServer(productList: MutableState<List<Product>>, networkState: MutableState<NetworkStatus>) {
        HttpClient().getApiResponse(productList,networkState)
    }

}