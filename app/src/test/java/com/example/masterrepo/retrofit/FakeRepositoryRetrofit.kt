package com.example.masterrepo.retrofit

import com.example.masterrepo.retrofit.model.ApiResponse
import com.example.masterrepo.retrofit.repository.RetrofitRepoInterface

class FakeRepositoryRetrofit : RetrofitRepoInterface {
    override suspend fun getCatFact(): ApiResponse? {
        return ApiResponse("This is a cat fact", 18)
    }
}