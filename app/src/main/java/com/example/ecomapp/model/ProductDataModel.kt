package com.example.ecomapp.model

import com.google.gson.annotations.SerializedName


data class ProductDataModel(@SerializedName("title") val title:String,
                           @SerializedName("description") val description: String,
                            @SerializedName("image") val image:String)



