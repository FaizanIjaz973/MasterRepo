package com.example.masterrepo.room.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: Entity)

    @Delete
    suspend fun delete(entity: Entity)

    @Query("SELECT * FROM dbtable")
    fun getAllEntries(): LiveData<List<Entity>>
}