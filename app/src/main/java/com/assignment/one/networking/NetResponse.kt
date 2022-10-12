package com.assignment.one.networking

import com.assignment.one.domain.model.Product

sealed class NetResponse{
    object Fetching: NetResponse()
    data class Success(val list: List<Product>) : NetResponse()
    data class Failed(val list: List<Product>) : NetResponse()
}
