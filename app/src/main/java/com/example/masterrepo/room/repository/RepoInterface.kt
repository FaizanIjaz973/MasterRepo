package com.example.masterrepo.room.repository

import androidx.lifecycle.LiveData
import com.example.masterrepo.room.db.Entity

interface RepoInterface {

    suspend fun insert(entry: Entity)

    suspend fun delete(entry: Entity)

    fun getAllEntries(): LiveData<List<Entity>>
}