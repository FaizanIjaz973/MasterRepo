package com.example.masterrepo.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.example.masterrepo.retrofit.api.RetrofitApi
import com.example.masterrepo.retrofit.repository.RetrofitRepoImplementation
import com.example.masterrepo.retrofit.repository.RetrofitRepoInterface
import com.example.masterrepo.retrofit.utils.RetrofitConstants.BASE_URL
import com.example.masterrepo.room.db.Database
import com.example.masterrepo.room.repository.RepoImplementation
import com.example.masterrepo.room.repository.RepoInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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

    @Provides
    @Singleton
    fun injectRetrofit() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(RetrofitApi::class.java)

    @Provides
    @Singleton
    fun injectRetrofitRepo(retrofit: RetrofitApi) = RetrofitRepoImplementation(retrofit) as RetrofitRepoInterface
}