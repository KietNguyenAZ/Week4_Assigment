package com.example.onboarding
import androidx.appcompat.app.ActionBar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onboarding.databinding.ActivityRestaurantMenuBinding
import com.example.onboarding.databinding.ActivitySuccessOrderBinding
import com.example.onboarding.models.RestaurentModel

class SuccessOrderActivity : AppCompatActivity() {
    lateinit var binding: ActivitySuccessOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restaurantModel: RestaurentModel? = intent.getParcelableExtra("RestaurantModel")
        val actionbar: ActionBar? = supportActionBar
        actionbar?.setTitle(restaurantModel?.name)
        actionbar?.setSubtitle(restaurantModel?.address)
        actionbar?.setDisplayHomeAsUpEnabled(false)

        binding.buttonDone.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
    }
}