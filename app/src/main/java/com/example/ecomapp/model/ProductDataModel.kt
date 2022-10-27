package com.example.ecomapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDataModel(@SerializedName("title") val title:String,
                           @SerializedName("description") val description: String,
                            @SerializedName("image") val image:String,
                            @SerializedName("price") val price:Float):Parcelable



