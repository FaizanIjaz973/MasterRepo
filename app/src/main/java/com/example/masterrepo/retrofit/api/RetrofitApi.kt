package com.example.masterrepo.retrofit.api

import com.example.masterrepo.retrofit.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

    @GET("/fact")
    suspend fun getCatFact(): Response<ApiResponse>
}