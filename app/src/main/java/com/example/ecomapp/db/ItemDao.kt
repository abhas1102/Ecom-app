package com.example.ecomapp.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * from `cart-db`")
    fun getItems():List<Entity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(item: Entity)

     @Delete
     fun deleteCart(items:List<Entity>)

     @Query("SELECT * from `myorder-db`")
     fun getItemsOfMyOrder():List<EntityMyOrder>

     @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertInMyOrder(item: EntityMyOrder)




}