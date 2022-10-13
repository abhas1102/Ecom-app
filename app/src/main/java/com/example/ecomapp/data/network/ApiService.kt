package com.example.ecomapp.data.network

import com.example.ecomapp.model.ProductDataModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("products/")
    suspend fun getProducts():Response<List<ProductDataModel>>
}