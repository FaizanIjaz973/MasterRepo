package com.example.masterrepo.room.repository

import androidx.lifecycle.LiveData
import com.example.masterrepo.room.db.Dao
import com.example.masterrepo.room.db.Entity
import javax.inject.Inject

class RepoImplementation @Inject constructor(private val dao: Dao): RepoInterface {

    override suspend fun insert(entry: Entity) {
        dao.insert(entry)
    }

    override suspend fun delete(entry: Entity) {
        dao.delete(entry)
    }

    override fun getAllEntries(): LiveData<List<Entity>> {
        return dao.getAllEntries()
    }
}