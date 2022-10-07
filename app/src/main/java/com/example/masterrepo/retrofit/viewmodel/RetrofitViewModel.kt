package com.example.masterrepo.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masterrepo.retrofit.model.ApiResponse
import com.example.masterrepo.retrofit.repository.RetrofitRepoInterface
import com.example.masterrepo.util.EspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RetrofitViewModel @Inject constructor(private val repo: RetrofitRepoInterface) : ViewModel() {

    val response = MutableLiveData<ApiResponse>()

    fun getCatFact(){
        viewModelScope.launch {
            EspressoIdlingResource.increment()
            response.postValue(repo.getCatFact())
            EspressoIdlingResource.decrement()
        }
    }
}