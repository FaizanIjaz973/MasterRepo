package com.example.masterrepo

import androidx.appcompat.app.AppCompatActivity
import com.example.masterrepo.retrofit.view.RetrofitActivity
import com.example.masterrepo.room.view.RoomActivity

object Constants {
    val entries = listOf<CardEntry>(
        CardEntry(
            "Room",
            "Deals with the room db",
            RoomActivity::class.java as Class<AppCompatActivity>
        ),
        CardEntry("Retrofit",
            "Deals with fetching data from API",
            RetrofitActivity::class.java as Class<AppCompatActivity>
        )
    )
}