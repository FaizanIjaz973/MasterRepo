package com.example.masterrepo.retrofit.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.masterrepo.MainCoroutineRule
import com.example.masterrepo.getOrAwaitValueTest
import com.example.masterrepo.retrofit.FakeRepositoryRetrofit
import com.example.masterrepo.retrofit.model.ApiResponse
import com.google.common.truth.Truth.assertThat

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RetrofitViewModelTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var retrofitViewModel : RetrofitViewModel

    @Before
    fun setup(){
        retrofitViewModel = RetrofitViewModel(FakeRepositoryRetrofit())
    }

    //For this test to pass, we need to comment out the EspressoIdlingResource.increment() and EspressoIdlingResource.decrement() which are used in the UI testing
    @Test
    fun `verify if the network call gets back some data`() = runBlocking{
        retrofitViewModel.getCatFact()
        assertThat(retrofitViewModel.response.getOrAwaitValueTest()).isEqualTo(ApiResponse("This is a cat fact", 18))
    }

}