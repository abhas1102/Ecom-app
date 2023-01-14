package com.example.ecomapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart-db")
data class Entity (
              @PrimaryKey(autoGenerate = true)
              val id: Int?=null,
              @ColumnInfo(name = "quantity")
              val quantity:Int,
              @ColumnInfo(name = "title")
              val title:String,
              @ColumnInfo(name = "price")
              val price:Float,
              @ColumnInfo(name = "image")
              val image:String,

)


