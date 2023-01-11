package com.example.ecomapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartProductDataModel(
    val quantity:Int,
    val title:String,
    val price:Float,
    val image:String
):Parcelable
