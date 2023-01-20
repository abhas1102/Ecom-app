package com.example.ecomapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "myorder-db")
data class EntityMyOrder(
    @PrimaryKey
    val id:Int,

    @ColumnInfo(name = "_quantity")
    val quantity:Int,

    @ColumnInfo(name = "_title")
    val title:String,

    @ColumnInfo(name = "_dateOfOrder")
    val date: String
)
