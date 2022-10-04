package com.example.masterrepo

import androidx.appcompat.app.AppCompatActivity

data class CardEntry (
    var title: String,
    var description: String,
    val c: Class<AppCompatActivity>,
)