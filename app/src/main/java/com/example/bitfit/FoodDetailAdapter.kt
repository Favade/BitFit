package com.example.bitfit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodDetailAdapter(
    private val context: Context,
    private val food: MutableList<FoodDetailsEntity>) : RecyclerView.Adapter<FoodDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.food_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodEntity = food[position]
        holder.bind(foodEntity)
    }

    override fun getItemCount() : Int{
        return food.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val foodName = itemView.findViewById<TextView>(R.id.tvFoodName)
        private val foodCalories = itemView.findViewById<TextView>(R.id.tvCalories)
       // private val caloriesTv = itemView.findViewById<TextView>(R.id.tvCalc)


        fun bind(foodItem: FoodDetailsEntity) {
            foodName.text = foodItem.foodName
            foodCalories.text = foodItem.foodCalories.toString()

        }
    }
}