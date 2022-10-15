package com.example.masterrepo

import androidx.appcompat.app.AppCompatActivity
import com.example.masterrepo.audioplayfromlocalstorage.AudioPlayFromLocalStorageActivity
import com.example.masterrepo.readwritefromresource.view.ReadWriteFromResourceActivity
import com.example.masterrepo.retrofit.view.RetrofitActivity
import com.example.masterrepo.room.view.RoomActivity
import com.example.masterrepo.sharedatawithotherapps.view.ShareDataWithOtherAppsActivity
import com.example.masterrepo.videoplayfromlocalstorage.VideoPlayFromLocalStorageActivity
import com.example.masterrepo.videostreamfrominternet.VideoStreamFromInternet

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
        ),
        CardEntry("ShareData",
            "Share data with external apps",
            ShareDataWithOtherAppsActivity::class.java as Class<AppCompatActivity>
        ),
        CardEntry("ReadWriteResource",
            "Read from and write to the resource",
            ReadWriteFromResourceActivity::class.java as Class<AppCompatActivity>
        ),
        CardEntry("VideoPlayFromLocalStorage",
            "Plays video stored in the local storage",
            VideoPlayFromLocalStorageActivity::class.java as Class<AppCompatActivity>
        ),
        CardEntry("AudioPlayFromLocalStorage",
            "Plays audio stored in the local storage",
            AudioPlayFromLocalStorageActivity::class.java as Class<AppCompatActivity>
        ),
        CardEntry("VideoStreamFromInternet",
            "Streams video from a URL",
            VideoStreamFromInternet::class.java as Class<AppCompatActivity>
        ),
    )
}