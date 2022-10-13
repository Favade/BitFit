package com.example.bitfit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "food_tracker")
data class FoodDetailsEntity(
    @ColumnInfo(name = "food_name") var foodName: String,
    @ColumnInfo(name = "food_calories") var foodCalories: Int,
    @PrimaryKey(autoGenerate = true) var id: Int =0,
)
