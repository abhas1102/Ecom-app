package com.example.ecomapp.model

data class MyOrderProductDataModel(
    val quantity:Int,
    val title: String,
    val otherDetails: OtherDetailsModel
)
data class OtherDetailsModel(
    val orderedDate: String?,
    val state: String,
    val pinCode: String,
    val paymentMode:String
    )
