package com.example.onboarding

import android.app.AlertDialog
import android.content.DialogInterface
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onboarding.databinding.ResViewBinding

class RestaurantAdapter(private val ListOfRestaurant: MutableList<Restaurant>):
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>()
{
    lateinit var binding: ResViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.res_view, parent, false)
        return RestaurantViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentRes = ListOfRestaurant[position]
        holder.bindData(currentRes)
    }

    override fun getItemCount(): Int {
        return ListOfRestaurant.size
    }




    inner class RestaurantViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindData(restaurant: Restaurant) {
            val nameRes : TextView = itemView.findViewById(R.id.restaurantName)
            val addressRes: TextView = itemView.findViewById(R.id.restaurantAddress)
            val avatarRes: ImageView = itemView.findViewById(R.id.restaurant_avt)
            nameRes.text = restaurant.restaurantName
            addressRes.text = restaurant.restaurantAddress
            Glide.with(itemView.context).load(restaurant.restaurantImageLink).centerCrop().into(avatarRes)
            itemView.setOnClickListener { deleteItem(adapterPosition, restaurant) }
        }
        private fun deleteItem(index: Int, restaurant: Restaurant){

            val builder = AlertDialog.Builder(itemView.context)
            builder.setMessage("Do you want to delete " + restaurant.restaurantName)
            builder.setPositiveButton("Yes") { dialog, which ->
                ListOfRestaurant.removeAt(index)
                notifyDataSetChanged()
            }
            builder.setNegativeButton("No"){ dialog, which ->

            }

            builder.show()

        }
    }
}