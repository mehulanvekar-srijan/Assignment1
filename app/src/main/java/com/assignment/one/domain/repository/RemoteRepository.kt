package com.assignment.one.domain.repository

import com.assignment.one.networking.HttpClient
import com.assignment.one.networking.NetResponse

/*
* RemoteRepository stores data fetched from API
* other classes/functions should access this object inorder to access productList
*/
object RemoteRepository {
    fun fetchFromServer(): NetResponse {
        return HttpClient().getApiResponse()
    }
}