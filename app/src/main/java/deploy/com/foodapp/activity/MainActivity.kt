package deploy.com.foodapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import deploy.com.foodapp.R
import deploy.com.foodapp.adapter.FoodAdapter
import deploy.com.foodapp.adapter.FoodTerbaruAdapter
import deploy.com.foodapp.databinding.ActivityMainBinding
import deploy.com.foodapp.viewmodel.FoodViewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: FoodViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterFood: FoodAdapter
    private lateinit var adapterFoodTerbaru: FoodTerbaruAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchListFood()
        adapterFood = FoodAdapter()
        adapterFoodTerbaru = FoodTerbaruAdapter()
        with(binding.rvRecomended) {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL , false)
            setHasFixedSize(true)
            adapter = adapterFood
        }
        with(binding.rvTerbaru) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = adapterFoodTerbaru
        }

        mainViewModel.listFood.observe(this) {
            adapterFood.setData(it)
        }
    }
    private fun fetchListFood() {
        mainViewModel.listFood.observe(this) { foods ->
            adapterFood.setData(foods)
            adapterFoodTerbaru.setData(foods)
        }

        mainViewModel.isLoading.observe(this) { isLoading ->
            with(binding) {
                progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }
}