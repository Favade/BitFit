package com.example.bitfit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FoodDetailsEntity::class], version = 1)
abstract class FoodDetailDatabase: RoomDatabase() {
    abstract fun foodDao(): FoodDetailDao
    companion object {

        @Volatile
        private var INSTANCE: FoodDetailDatabase? = null

        fun getInstance(context: Context): FoodDetailDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FoodDetailDatabase::class.java, "foodtracker-db"
            ).build()
    }
}