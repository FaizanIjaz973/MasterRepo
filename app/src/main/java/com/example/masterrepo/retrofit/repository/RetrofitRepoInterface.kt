package com.example.masterrepo.retrofit.repository

import com.example.masterrepo.retrofit.model.ApiResponse

interface RetrofitRepoInterface  {

    //For fetching the data from the API using retrofit
    suspend fun getCatFact() : ApiResponse?
}