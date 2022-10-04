package com.example.masterrepo.room.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dbtable")
data class Entity(
    var firstName: String,
    var lastName: String,
    var age: Int,
    var sex: Char,
    @PrimaryKey(autoGenerate = true)
    val id: Int
)
