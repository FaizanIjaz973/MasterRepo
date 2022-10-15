package com.example.masterrepo.audioplayfromlocalstorage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.masterrepo.R
import com.example.masterrepo.videoplayfromlocalstorage.UriUtils
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

class AudioPlayFromLocalStorageActivity: AppCompatActivity() {
    private lateinit var player: ExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audioplayfromlocalstorage)
        player = ExoPlayer.Builder(this).build()
        val playerView = findViewById<StyledPlayerView>(R.id.audioPlayFromLocalStorage_playerView)
        // Bind the player to the view.
        playerView.setPlayer(player);
        // MP3 Audio. Using the same UriUtils class from the package 'videoplayfromlocalstorage'
        val uri = UriUtils.getUriToResource(this, R.raw.samplemusic)
        // Build the media item.
        val mediaItem: MediaItem = MediaItem.fromUri(uri)
        // Set the media item to be played.
        player.setMediaItem(mediaItem)
        // Prepare the player.
        player.prepare()
        player.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}