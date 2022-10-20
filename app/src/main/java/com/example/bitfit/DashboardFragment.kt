package com.example.bitfit
import android.content.Intent
import java.util.Timer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import java.sql.Time
import kotlin.concurrent.schedule
import kotlin.system.exitProcess

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        lifecycleScope.launch(Dispatchers.IO) {
            val min = (activity?.application as FoodApplication).database.foodDao().minCalories()
            view.findViewById<TextView?>(R.id.tvMinCalories).text = min.toString()

            val max = (activity?.application as FoodApplication).database.foodDao().maxCalories()
            view.findViewById<TextView?>(R.id.tvMaxCalories).text = max.toString()

            val avg = (activity?.application as FoodApplication).database.foodDao().averageCalories()
            view.findViewById<TextView?>(R.id.tvAvgCalories).text = avg.toString()
        }

        view.findViewById<Button>(R.id.btnClearData).setOnClickListener {
            view.findViewById<TextView?>(R.id.tvMinCalories).text = "0"
            view.findViewById<TextView?>(R.id.tvMaxCalories).text = "0"
            view.findViewById<TextView?>(R.id.tvAvgCalories).text = "0"
            Timer().schedule(2000) {
                activity?.let {
                    val intent = Intent(it, AddFoodActivity::class.java)
                    it.startActivity(intent)
                }

            }
        }

        return view

    }

}


