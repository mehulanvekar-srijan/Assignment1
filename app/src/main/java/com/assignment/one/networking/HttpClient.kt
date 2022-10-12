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

    fun getApiResponse() : List<Product> {
        Log.d("textMX", "getApiResponse")
        val response : Response<List<Product>> = retrofitData.execute()
        return response.body() ?: listOf()
    }
}