package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    lateinit var wishes: MutableList<WishItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var wishrv =  findViewById<RecyclerView>(R.id.wishrv)
        //fill the emails
        wishes = mutableListOf()

        //create instance of adapter
        val adapter = WishItemAdapter(wishes)

        wishrv.adapter =  adapter

        wishrv.layoutManager = LinearLayoutManager(this)
        val button =  findViewById<Button>(R.id.button)

        button.setOnClickListener{

            if(findViewById<EditText>(R.id.itemname).text.isNotEmpty()&& findViewById<EditText>(R.id.price).text.isNotEmpty()&& findViewById<EditText>(R.id.url).text.isNotEmpty()){
                val itemName =  findViewById<EditText>(R.id.itemname).text.toString()
                val price =  findViewById<EditText>(R.id.price).text.toString().toDouble()
                val url =  findViewById<EditText>(R.id.url).text.toString()
                wishes.add(WishItem(itemName,price,url))
                findViewById<EditText>(R.id.itemname).text.clear()
                findViewById<EditText>(R.id.price).text.clear()
                findViewById<EditText>(R.id.url).text.clear()
                adapter.notifyDataSetChanged();
            }


        }
    }
}