package com.example.ecomapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomapp.data.Repository
import com.example.ecomapp.model.ProductDataModel
import kotlinx.coroutines.launch

class HomeFragmentViewModel:ViewModel() {
   // private val repository = Repository()

    private val repository = Repository()

    private val _products = MutableLiveData<List<ProductDataModel>>()
    val products:LiveData<List<ProductDataModel>> = _products

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            val data = repository.getProducts()
            _products.postValue(data)
        }
    }
}