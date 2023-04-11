package com.example.onboarding
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onboarding.adapter.PlaceYourOrderAdapter
import com.example.onboarding.models.RestaurentModel
import com.example.onboarding.databinding.ActivityPlaceYourOrderBinding

class PlaceYourOrderActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlaceYourOrderBinding
    var placeYourOrderAdapter: PlaceYourOrderAdapter? = null
    var isDeliveryOn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceYourOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restaurantModel: RestaurentModel? = intent.getParcelableExtra("RestaurantModel")
        supportActionBar?.apply {
            setTitle(restaurantModel?.name)
            setSubtitle(restaurantModel?.address)
            setDisplayHomeAsUpEnabled(true)
        }

        initRecyclerView(restaurantModel)
        calculateTotalAmount(restaurantModel)

        binding.buttonPlaceYourOrder.setOnClickListener {
            onPlaceOrderButtonCLick(restaurantModel)
        }

        binding.switchDelivery.setOnCheckedChangeListener { _, isChecked ->
            binding.apply {
                inputAddress.visibility = if (isChecked) View.VISIBLE else View.GONE
                inputCity.visibility = if (isChecked) View.VISIBLE else View.GONE
                inputState.visibility = if (isChecked) View.VISIBLE else View.GONE
                inputZip.visibility = if (isChecked) View.VISIBLE else View.GONE
                tvDeliveryCharge.visibility = if (isChecked) View.VISIBLE else View.GONE
                tvDeliveryChargeAmount.visibility = if (isChecked) View.VISIBLE else View.GONE
                isDeliveryOn = isChecked
                calculateTotalAmount(restaurantModel)
            }
        }
    }

    private fun initRecyclerView(restaurantModel: RestaurentModel?) {
        binding.cartItemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@PlaceYourOrderActivity)
            placeYourOrderAdapter = PlaceYourOrderAdapter(restaurantModel?.menus)
            adapter = placeYourOrderAdapter
        }
    }

    private fun calculateTotalAmount(restaurantModel: RestaurentModel?) {
        var subTotalAmount = 0f
        restaurantModel?.menus?.forEach { menu ->
            subTotalAmount += menu?.price!! * menu?.totalInCart!!
        }
        binding.tvSubtotalAmount.text = "$${"%.2f".format(subTotalAmount)}"
        if (isDeliveryOn) {
            binding.tvDeliveryChargeAmount.text = "$${restaurantModel?.delivery_charge?.toFloat()}"
            subTotalAmount += restaurantModel?.delivery_charge?.toFloat()!!
        }
        binding.tvTotalAmount.text = "$${"%.2f".format(subTotalAmount)}"
    }

    private fun onPlaceOrderButtonCLick(restaurantModel: RestaurentModel?) {
        with(binding) {
            when {
                inputName.text.isNullOrEmpty() -> inputName.error = "Enter your name"
                isDeliveryOn && (inputAddress.text.isNullOrEmpty() || inputCity.text.isNullOrEmpty() || inputZip.text.isNullOrEmpty()) -> {
                    inputAddress.error = "Enter your address"
                    inputCity.error = "Enter your City Name"
                    inputZip.error = "Enter your Zip code"
                }
                inputCardNumber.text.isNullOrEmpty() -> inputCardNumber.error = "Enter your credit card number"
                inputCardExpiry.text.isNullOrEmpty() -> inputCardExpiry.error = "Enter your credit card expiry"
                inputCardPin.text.isNullOrEmpty() -> inputCardPin.error = "Enter your credit card pin/cvv"
                else -> {
                    val intent = Intent(this@PlaceYourOrderActivity, SuccessOrderActivity::class.java).apply {
                        putExtra("RestaurantModel", restaurantModel)
                    }
                    startActivity(intent)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1000) setResult(RESULT_OK)
        super.onActivityResult(requestCode, resultCode, data)
        finish()
    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED)
        finish()
    }

}