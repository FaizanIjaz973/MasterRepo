/*
Created by: Faizan Ijaz
Email id: faizanijaz@gmail.com
Dated: 10/1/2022
This is the entry point of the application.
It contains the navigation point of all the modules for the application in cards view.
*/
package com.example.masterrepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
    }
}