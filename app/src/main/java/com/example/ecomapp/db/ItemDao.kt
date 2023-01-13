package com.example.ecomapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Entity)

    @Query("SELECT * from item WHERE id = :id")
    fun getItems():Flow<List<Entity>>


}