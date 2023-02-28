package com.example.onboarding

import android.os.Bundle
import android.text.style.RelativeSizeSpan
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.onboarding.databinding.RestaurantMainBinding

class RestaurantMainActivity: AppCompatActivity() {
    lateinit var binding: RestaurantMainBinding
    lateinit var adapter: RestaurantAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.restaurant_main)
        setUpRecycleView()
    }

    private fun setUpRecycleView(){
        binding.recyclerViewRestaurant.layoutManager = LinearLayoutManager(this)
        adapter = RestaurantAdapter(RestaurantDataStore.getDataSet())
        binding.recyclerViewRestaurant.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.res_view_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.linear -> {
                binding.recyclerViewRestaurant.layoutManager = LinearLayoutManager(this)
                adapter = RestaurantAdapter(RestaurantDataStore.getDataSet())
                binding.recyclerViewRestaurant.adapter = adapter
                true
            }
            R.id.grid -> {
                binding.recyclerViewRestaurant.layoutManager = GridLayoutManager(this, 2)
                adapter = RestaurantAdapter(RestaurantDataStore.getDataSet())
                binding.recyclerViewRestaurant.adapter = adapter
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }




    }

}