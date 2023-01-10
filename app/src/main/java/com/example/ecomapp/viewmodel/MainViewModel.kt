package com.example.ecomapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomapp.data.Repository
import com.example.ecomapp.model.ProductDataModel
import com.example.ecomapp.model.ProductsData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.w3c.dom.ls.LSException

class MainViewModel: ViewModel(){

    private val repository = Repository()

    private val _products = MutableLiveData<ArrayList<ProductDataModel>>()
    val products:LiveData<ArrayList<ProductDataModel>> = _products



    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private var quantity_value = 0
    var quantityValue = quantity_value

    private var updated_quantity_value = 0
    var updatedQuantityValue = 0

    private var price_product = 0
    var priceProduct = 0

    private var updated_price_product = 0
    var updatedPriceProduct = 0

    private var list_ids = arrayListOf<Int>()
    var listIds = list_ids

   // private var map_id_quantity = mutableMapOf<Int,Int>()
  //  private var map_id_quantity = mutableMapOf<Int,Pair<Int,Float>>()
    private var map_id_quantity = mutableMapOf<Int,Triple<Int,Float,String>>()
  // private var map_id_quantity = mutableMapOf<Int,ProductDataModel>() -> This can be done to fetch more details about product
    var mapIdQuantity = map_id_quantity

    private var map_id_quantity_for_my_order = mutableMapOf<Int,Triple<Int,Float,String>>() // New map for MyOrder Screen.
    var mapIdQuantityForMyOrder = map_id_quantity_for_my_order

    var stateOfOrder = ""
    var pinCodeOfOrder = ""
    var dateOfOrder = ""
    var paymentMode = ""




    init {
        viewModelScope.launch {
            delay(3000)
            _isLoading.value = false
        }
        getProducts()

    }
    fun addQuantityValue(): Int? {
        quantity_value = quantityValue + 1
        return quantity_value
    }

   /* fun productPrice(quantity:Int):Float {
        _price.value =


    } */
   private fun getProducts() {
       viewModelScope.launch {
           val data = repository.getProducts()
           _products.postValue(data as ArrayList)
       }
   }

}