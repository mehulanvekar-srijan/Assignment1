package com.assignment.one.domain.repository

import com.assignment.one.domain.model.Product
import com.assignment.one.networking.HttpClient

val remoteRepository = RemoteRepository

/*
* RemoteRepository stores data fetched from API
* other classes/functions should access this object inorder to access productList
*/
object RemoteRepository {

    private var productList: List<Product> = listOf()

    fun getProductList(): List<Product> = productList
    fun setProductList(list: List<Product>) { productList = list }
    fun fetchFromServer() = HttpClient().getApiResponse()

}