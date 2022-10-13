package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    val data = mutableListOf<FoodDetailsEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val foodRv = findViewById<RecyclerView>(R.id.rvFoodItems)
        val addNewMain = findViewById<Button>(R.id.btnAddNewFood)
        foodRv.layoutManager = LinearLayoutManager(this)
        foodRv.adapter = FoodDetailAdapter(this,data)

        addNewMain.setOnClickListener {
            Intent(this, AddFoodActivity::class.java).also {
                startActivity(it)
            }
        }

        lifecycleScope.launch {
            (application as FoodApplication).database.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    FoodDetailsEntity(
                        entity.foodName,
                        entity.foodCalories
                    )
                }.also { mappedList ->
                    data.clear()
                    data.addAll(mappedList)
                    foodRv.adapter ?.notifyDataSetChanged()
                }
            }
        }
    }


}