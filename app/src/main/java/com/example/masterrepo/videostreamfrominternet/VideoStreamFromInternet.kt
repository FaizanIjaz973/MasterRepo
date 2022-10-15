package com.example.masterrepo.videostreamfrominternet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.masterrepo.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

class VideoStreamFromInternet: AppCompatActivity() {

    private lateinit var player : ExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videostreamfrominternet)
        player = ExoPlayer.Builder(this).build()
        val playerView = findViewById<StyledPlayerView>(R.id.videoStreamFromInternet_playerView)
        // Bind the player to the view.
        playerView.player = player
        // Online video streaming
        val uri = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        // Build the media item.
        val mediaItem: MediaItem = MediaItem.fromUri(uri)
        // Set the media item to be played.
        player.setMediaItem(mediaItem)
        // Prepare the player.
        player.prepare()
        // Required when streaming from a remote location
        player.playWhenReady = true
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}