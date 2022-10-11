package com.example.masterrepo.sharedatawithotherapps.view

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.masterrepo.R
import com.example.masterrepo.sharedatawithotherapps.MyBroadCastReceiver

// For this particular activity, we don't need to use viewModel and repository layers as it is a simple matter of sharing and receiving text with and from external applications
class ShareDataWithOtherAppsActivity : AppCompatActivity() {

    lateinit var textToBeShared: TextView
    lateinit var shareButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sharedatawithotherapps)
        initViews()

        // Handles data that is being shared by the other apps
        handleIncomingText()
    }

    private fun handleIncomingText(){
        when (intent?.action) {
            Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type){
                    textToBeShared.text = intent.getStringExtra(Intent.EXTRA_TEXT)
                }
            }
        }
    }
    
    private fun initViews(){
        textToBeShared = findViewById(R.id.sharedatawithotherapps_txtTobeShared)
        shareButton = findViewById(R.id.sharedatawithotherapps_shareButton)

        shareButton.setOnClickListener(View.OnClickListener {
            //sendSimpleText()
            sendTextWithCallBack()
        })

    }

    // Shares a piece of text with any app that the text can be shared with
    // Additionally in the MyBroadCastReceiver, it can be seen with which app has the user chosen to share the text with
    private fun sendTextWithCallBack(){
        var share: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, textToBeShared.text)
            type = "text/plain"
        }

        val pi = PendingIntent.getBroadcast(
            this, 2,
            Intent(this, MyBroadCastReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        share = Intent.createChooser(share, null, pi.intentSender)
        startActivity(share)
    }

    // Shares a piece of text with any app that the text can be shared with
    private fun sendSimpleText(){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, textToBeShared.text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

}