package com.assignment.one.domain.repository

import com.assignment.one.domain.model.Product
import com.assignment.one.networking.HttpClient
import com.assignment.one.networking.NetResponse
import com.assignment.one.networking.NetworkStatus

/*
* RemoteRepository stores data fetched from API
* other classes/functions should access this object inorder to access productList
*/
object RemoteRepository {
    fun fetchFromServer(): NetResponse {
        return HttpClient().getApiResponse()
    }

    fun fetchFromServer(
        mutateProductList : (List<Product>)->Unit,
        mutateNetworkStatus : (NetworkStatus)->Unit
    ) {
        return HttpClient().getApiResponse(mutateProductList,mutateNetworkStatus)
    }
}