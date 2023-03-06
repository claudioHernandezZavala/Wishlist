package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WishItemAdapter(private val wishItems:MutableList<WishItem>) : RecyclerView.Adapter<WishItemAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val name = itemView.findViewById<TextView>(R.id.name)
        val itemUrl = itemView.findViewById<TextView>(R.id.itemUrl)
        val itemPrice = itemView.findViewById<TextView>(R.id.itemPrice)
        val card =  itemView.findViewById<CardView>(R.id.itemCard)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val wishItemView = inflater.inflate(R.layout.wishitem, parent, false)
        // Return a new holder instance
        return ViewHolder(wishItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val wish = wishItems.get(position)
        // Set item views based on views and data model
        holder.name.text = wish.name
        holder.itemPrice.text = wish.price.toString()
        holder.itemUrl.text = wish.url
        holder.itemUrl.setClickable(true);
        holder.itemUrl.setFocusable(false);
        holder.itemUrl.setInputType(InputType.TYPE_NULL);
        holder.itemUrl.setOnClickListener({
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(wish.url))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL ", Toast.LENGTH_LONG).show()
            }
        })
//        holder.card.setClickable(true);
//        holder.card.setFocusable(false);
        holder.card.setOnLongClickListener{
            wishItems.removeAt(position)
            this.notifyDataSetChanged()
             true

        }



    }

    override fun getItemCount(): Int {
        return wishItems.size;
    }
}