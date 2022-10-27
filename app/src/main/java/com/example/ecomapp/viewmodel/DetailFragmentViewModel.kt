package com.example.ecomapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecomapp.data.Repository
import com.example.ecomapp.model.ProductDataModel

class DetailFragmentViewModel:ViewModel() {

    private val repository = Repository()

    private val _products = MutableLiveData<ProductDataModel>()
    val products: LiveData<ProductDataModel> = _products

    init {
        getProduct()
    }

    private fun getProduct() {

    }
}