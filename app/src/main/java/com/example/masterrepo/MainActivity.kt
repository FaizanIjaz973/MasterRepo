/*
Created by: Faizan Ijaz
Email id: faizanijaz@gmail.com
Dated: 10/1/2022
This is the entry point of the application.
It contains the navigation point of all the modules for the application in cards view.
*/
package com.example.masterrepo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val logTag = "MainActivity"
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializing recyclerview
        recyclerView = findViewById(R.id.recyclerViewMainActivity)
        var adapter = RecyclerViewAdapter()
        adapter.entries = Constants.entries
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.clickedItem.observe(this, Observer{
            startActivity(Intent(this, it))
            //startActivity(Intent(this, RoomActivity::class.java ))
        })
    }
}