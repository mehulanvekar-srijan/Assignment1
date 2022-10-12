package com.assignment.one.networking

import android.util.Log
import androidx.compose.runtime.MutableState
import com.assignment.one.domain.model.Product
import com.assignment.one.domain.repository.RemoteRepository
import com.assignment.one.viewmodel.HomeScreenViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_API_STORE_URL: String = "https://fakestoreapi.com/"

class HttpClient{

    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_API_STORE_URL)
        .build()
        .create(RetrofitInterface::class.java)

    private val retrofitData = retrofitBuilder.getProductData()

    //Fill data inside productList present in RemoteRepository, on failure fill empty list
    fun getApiResponse(productList: MutableState<List<Product>>,networkState: MutableState<NetworkStatus>){
        Log.d("MehulX", "getApiResponse: MS")

        retrofitData.enqueue(object : Callback<List<Product>?> {
            override fun onResponse(call: Call<List<Product>?>, response: Response<List<Product>?>){
                productList.value = response.body() ?: emptyList()
                networkState.value = NetworkStatus.Success
                Log.d("MehulX", "onResponse: ${productList.value}")
            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                productList.value =  emptyList()
                networkState.value = NetworkStatus.Failed
                Log.d("MehulX", "onFailure: ${t.message}")
            }
        })

    }
}