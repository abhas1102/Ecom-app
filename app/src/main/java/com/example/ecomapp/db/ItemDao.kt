package com.example.ecomapp.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * from `cart-db`")
    fun getItems():List<Entity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(item: Entity)

     @Query("SELECT id FROM `cart-db`")
     fun getIds():List<Int>

    // @Query("SELECT quantity FROM `cart-db` WHERE id = id")
   //  fun getQuantity(id:String):String

     @Delete
     fun deleteCart(items:List<Entity>)

     @Query("SELECT * from `myorder-db`")
     fun getItemsOfMyOrder():List<EntityMyOrder>

     @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertInMyOrder(item: EntityMyOrder)




}