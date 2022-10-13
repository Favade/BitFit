package com.example.bitfit

import android.app.Application
import androidx.room.Room

class FoodApplication : Application() {
    val database: FoodDetailDatabase by lazy {
        Room.databaseBuilder(
            this,
            FoodDetailDatabase::class.java,
            "database.db"
        ).allowMainThreadQueries().build()
    }
}