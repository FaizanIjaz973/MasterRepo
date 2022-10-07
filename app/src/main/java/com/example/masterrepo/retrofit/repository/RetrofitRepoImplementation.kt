package com.example.masterrepo.retrofit.repository

import com.example.masterrepo.retrofit.api.RetrofitApi
import com.example.masterrepo.retrofit.model.ApiResponse
import javax.inject.Inject

class RetrofitRepoImplementation @Inject constructor(private val retrofit: RetrofitApi): RetrofitRepoInterface {
    override suspend fun getCatFact(): ApiResponse? {
        try {
            val response = retrofit.getCatFact()
            if (response.isSuccessful) {
                return response.body()!!
            }
        }catch (Ex:Exception){ }
        return null
    }
}