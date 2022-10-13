package com.example.ecomapp.data

import com.example.ecomapp.data.network.ApiService
import com.example.ecomapp.data.network.RetrofitInstance
import com.example.ecomapp.model.ProductDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository {
    val productApi = RetrofitInstance.getInstance().create(ApiService::class.java)

    suspend fun getProducts(): List<ProductDataModel>? {
        return withContext(Dispatchers.IO) {
            var result = productApi.getProducts()
            result.body()
        }
    }
}