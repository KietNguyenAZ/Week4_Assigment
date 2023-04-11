package com.example.onboarding
import androidx.appcompat.app.ActionBar
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onboarding.adapter.MenuListAdapter
import com.example.onboarding.databinding.ActivityRestaurantMenuBinding
import com.example.onboarding.models.Menus
import com.example.onboarding.models.RestaurentModel

class RestaurantMenuActivity : AppCompatActivity(), MenuListAdapter.MenuListClickListener {
    lateinit var binding: ActivityRestaurantMenuBinding
    private var itemsInTheCartList: MutableList<Menus?>? = null
    private var totalItemInCartCount = 0
    private  var menuList: List<Menus?>? = null
    private var menuListAdapter: MenuListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restaurantModel = intent?.getParcelableExtra<RestaurentModel>("RestaurantModel")

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setTitle(restaurantModel?.name)
        actionBar?.setSubtitle(restaurantModel?.address)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        menuList = restaurantModel?.menus

        initRecyclerView(menuList)
        binding.checkoutButton.setOnClickListener {
            if(itemsInTheCartList != null && itemsInTheCartList!!.size <= 0) {
                Toast.makeText(this@RestaurantMenuActivity, "Please add some items in cart", Toast.LENGTH_LONG).show()
            }
            else {
                restaurantModel?.menus = itemsInTheCartList
                val intent = Intent(this@RestaurantMenuActivity, PlaceYourOrderActivity::class.java)
                intent.putExtra("RestaurantModel", restaurantModel)
                startActivityForResult(intent, 1000)
            }
        }

    }
    private fun initRecyclerView(menus: List<Menus?>?) {
        val menuRecyclerVuew = findViewById<RecyclerView>(R.id.menuRecyclerVuew)
        menuRecyclerVuew.layoutManager = GridLayoutManager(this, 2)
        menuListAdapter = MenuListAdapter(menus, this)
        menuRecyclerVuew.adapter =menuListAdapter
    }

    override fun addToCartClickListener(menu: Menus) {
        if(itemsInTheCartList == null) {
            itemsInTheCartList = ArrayList()
        }
        itemsInTheCartList?.add(menu)
        totalItemInCartCount = 0
        for(menu in itemsInTheCartList!!) {
            totalItemInCartCount = totalItemInCartCount + menu?.totalInCart!!
        }
        binding.checkoutButton.text = "Checkout (" + totalItemInCartCount +") Items"

    }

    override fun updateCartClickListener(menu: Menus) {
        val index = itemsInTheCartList!!.indexOf(menu)
        itemsInTheCartList?.removeAt(index)
        itemsInTheCartList?.add(menu)
        totalItemInCartCount = 0
        for(menu in itemsInTheCartList!!) {
            totalItemInCartCount = totalItemInCartCount + menu?.totalInCart!!
        }
        binding.checkoutButton.text = "Checkout (" + totalItemInCartCount +") Items"
    }

    override fun removeFromCartClickListener(menu: Menus) {
        if(itemsInTheCartList!!.contains(menu)) {
            itemsInTheCartList?.remove(menu)
            totalItemInCartCount = 0
            for(menu in itemsInTheCartList!!) {
                totalItemInCartCount += menu?.totalInCart!!
            }
            binding.checkoutButton.text = "Checkout (" + totalItemInCartCount +") Items"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1000 && resultCode == RESULT_OK) {
            finish()
        }
    }


}