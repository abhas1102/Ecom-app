package com.example.ecomapp.model


data class ProductDataModel(val title:String, val description: String, val price:Int,
                   val category:CategoryModel, val images:Array<String>)


data class CategoryModel(val name: String)

