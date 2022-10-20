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

    @Query("SELECT AVG(food_calories) as calories FROM food_tracker")
    fun averageCalories(): Int

    @Query("SELECT MIN(food_calories) as calories FROM food_tracker")
    fun minCalories(): Int

    @Query("SELECT MAX(food_calories) as calories FROM food_tracker")
    fun maxCalories(): Int
}