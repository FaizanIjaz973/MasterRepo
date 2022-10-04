package com.example.masterrepo.dependencyinjection

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import com.example.masterrepo.room.db.Database
import com.example.masterrepo.room.repository.RepoImplementation
import com.example.masterrepo.room.repository.RepoInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun injectRoomDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, Database::class.java,"dbtable").build()

    @Provides
    @Singleton
    fun injectDao(database: Database) = database.Dao()

    @Provides
    @Singleton
    fun injectRepo(dao: com.example.masterrepo.room.db.Dao) = RepoImplementation(dao) as RepoInterface
}