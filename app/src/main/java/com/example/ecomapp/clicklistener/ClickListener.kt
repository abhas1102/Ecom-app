package com.example.ecomapp.clicklistener

import com.example.ecomapp.data.Repository
import com.example.ecomapp.model.ProductDataModel

interface ClickListener {

    fun onClick(itemProduct: ProductDataModel)
}