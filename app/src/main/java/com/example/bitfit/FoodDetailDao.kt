package com.example.bitfit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDetailDao {
    @Query("SELECT * FROM food_tracker")
    fun getAll(): Flow<List<FoodDetailsEntity>>

    @Insert
    fun insertAll(foodList: List<FoodDetailsEntity>)

    @Insert
    fun insert(foodItem: FoodDetailsEntity)

    @Query("DELETE FROM food_tracker")
    fun deleteAll()
}