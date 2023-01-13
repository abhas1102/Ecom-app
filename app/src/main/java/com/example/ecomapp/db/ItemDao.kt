package com.example.ecomapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * from entity")
    fun getItems():List<Entity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(item: Entity)




}