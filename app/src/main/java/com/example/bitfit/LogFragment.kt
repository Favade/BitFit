package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class LogFragment : Fragment() {
    private val data = mutableListOf<FoodDetailsEntity>()
    private lateinit var foodDetailRecyclerView: RecyclerView
    private lateinit var foodDetailAdapter: FoodDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_log, container, false)

        val layoutManager = LinearLayoutManager(context)

        foodDetailRecyclerView = view.findViewById(R.id.rvFoodItems)
        foodDetailRecyclerView.layoutManager = layoutManager

        foodDetailAdapter = FoodDetailAdapter(view.context, data)

        lifecycleScope.launch {
            (activity?.application as FoodApplication).database.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    FoodDetailsEntity(
                        entity.foodName,
                        entity.foodCalories
                    )
                }.also { mappedList ->
                    data.clear()
                    data.addAll(mappedList)
                    foodDetailRecyclerView.adapter ?.notifyDataSetChanged()
                }
            }
        }

        foodDetailRecyclerView.adapter = foodDetailAdapter


        view.findViewById<Button>(R.id.btnAddNewFood).setOnClickListener {
            Intent(context,AddFoodActivity::class.java).also {
                startActivity(it)
            }
        }


        return view
    }

}
