package com.example.masterrepo.readwritefromresource.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.masterrepo.BaseApplication
import com.example.masterrepo.readwritefromresource.Constants
import com.example.masterrepo.readwritefromresource.repository.ReadWriteFromResourceRepo
import com.example.masterrepo.readwritefromresource.repository.ReadWriteFromResourceRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReadWriteFromResourceViewModel @Inject constructor(private val repo: ReadWriteFromResourceRepo) : ViewModel() {

    var stringReadFromResource = MutableLiveData<String>()

    var errorMessage = MutableLiveData<String>()

    fun writeToResource(string: String){
        if (string.isEmpty()){
            errorMessage.postValue("Can not write empty string")
            return
        }
        viewModelScope.launch {
            repo.writeToResource(string, Constants.fileName)
        }
    }

    fun readFromResource(){
        val list = readFilesList()
        if (list.contains(Constants.fileName)){
            viewModelScope.launch {
                val s = repo.readFromResource(Constants.fileName)
                if (s.isNotEmpty())
                    stringReadFromResource.postValue(s)
                else
                    errorMessage.postValue("Nothing written to file.")
            }
        }else
            errorMessage.postValue("Error: Write to file first.")
    }

    private fun readFilesList(): Array<String>{
        return repo.getFilesList()
    }
}