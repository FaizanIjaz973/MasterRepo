package com.example.masterrepo.videoplayfromlocalstorage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.masterrepo.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

class VideoPlayFromLocalStorageActivity : AppCompatActivity() {
    lateinit var player: ExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videoplayfromlocalstorage)
        setupPlayer()
    }

    private fun setupPlayer(){
        player = ExoPlayer.Builder(this).build()
        val playerView = findViewById<StyledPlayerView>(R.id.videoPlayFromLocalStorage_playerView)
        // Bind the player to the view.
        playerView.setPlayer(player);
        // MP4 video
        val uri = UriUtils.getUriToResource(this, R.raw.cartoons)
        val mediaItem: MediaItem = MediaItem.fromUri(uri)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}