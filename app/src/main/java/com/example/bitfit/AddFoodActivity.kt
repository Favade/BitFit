package com.example.bitfit

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val TAG = "AddFoodActivity"
private lateinit var foodEditText: EditText
private lateinit var caloriesEditText: EditText
class AddFoodActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        val enterFoodButton =  findViewById<Button>(R.id.btnAddFood)

        foodEditText = findViewById(R.id.etEnterFood)
        caloriesEditText  = findViewById(R.id.etEnterCalories)

        enterFoodButton.setOnClickListener {
            lifecycleScope.launch(IO) {
                (application as FoodApplication).database.foodDao().insert(
                    FoodDetailsEntity(foodName = foodEditText.text.toString(), foodCalories = caloriesEditText.text.toString().toInt())
                )
            }

            super.finish()
        }



    }
}