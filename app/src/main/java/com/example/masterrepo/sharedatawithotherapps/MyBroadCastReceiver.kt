package com.example.masterrepo.sharedatawithotherapps

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_CHOSEN_COMPONENT
import android.util.Log
import javax.inject.Inject

class MyBroadCastReceiver @Inject constructor() : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val clickedComponent : ComponentName = intent?.getParcelableExtra(EXTRA_CHOSEN_COMPONENT)!!;
        Log.d("MyBroadCastReceiver", clickedComponent.toString())
    }
}