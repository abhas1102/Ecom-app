package com.example.ecomapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "myorder-db")
data class EntityMyOrder(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,

    @ColumnInfo(name = "quantity")
    val quantity:Int,

    @ColumnInfo(name = "title")
    val title:String,

    @ColumnInfo(name = "dateOfOrder")
    val date: String
)
