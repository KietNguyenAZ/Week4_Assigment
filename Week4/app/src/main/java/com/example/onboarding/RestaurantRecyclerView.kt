package com.example.onboarding
import androidx.appcompat.app.ActionBar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onboarding.adapter.RestaurantListAdapter
import com.example.onboarding.models.RestaurentModel
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.io.StringWriter
import java.io.Writer

class RestaurantRecyclerView  : AppCompatActivity(), RestaurantListAdapter.RestaurantListClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant_recylerview)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setTitle("Restaurant List")

        val restaurantModel = getRestaurantData()
        initRecyclerView(restaurantModel)


    }

    private fun initRecyclerView(restaurantList: List<RestaurentModel?>?) {
        val recyclerViewRestaurant = findViewById<RecyclerView>(R.id.recyclerViewRestaurant)
        recyclerViewRestaurant.layoutManager = LinearLayoutManager(this)
        val adapter = RestaurantListAdapter(restaurantList, this)
        recyclerViewRestaurant.adapter =adapter
    }

    private fun getRestaurantData(): List<RestaurentModel?>? {
        val inputStream: InputStream = resources.openRawResource(R.raw.restaurent)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var n : Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)

            }

        }catch (e: Exception){}
        val jsonStr: String = writer.toString()
        val gson = Gson()
        val restaurantModel = gson.fromJson<Array<RestaurentModel>>(jsonStr, Array<RestaurentModel>::class.java).toList()

        return restaurantModel
    }

    override fun onItemClick(restaurantModel: RestaurentModel) {
        val intent = Intent(this, RestaurantMenuActivity::class.java)
        intent.putExtra("RestaurantModel", restaurantModel)
        startActivity(intent)
    }
}