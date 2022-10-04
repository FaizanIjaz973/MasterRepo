package com.example.masterrepo.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.masterrepo.room.db.Entity
import com.example.masterrepo.room.repository.RepoInterface

class FakeRepositoryRoom : RepoInterface {
    private val list = mutableListOf<Entity>()
    private val liveData = MutableLiveData<List<Entity>>(list)

    override suspend fun insert(entry: Entity) {
        list.add(entry)
        refreshData()
    }

    override suspend fun delete(entry: Entity) {
        list.remove(entry)
        refreshData()
    }

    override fun getAllEntries(): LiveData<List<Entity>> {
        return liveData
    }

    private fun refreshData(){
        liveData.postValue(list)
    }
}