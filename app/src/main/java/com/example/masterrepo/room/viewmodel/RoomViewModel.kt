package com.example.masterrepo.room.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masterrepo.room.db.Entity
import com.example.masterrepo.room.repository.RepoImplementation
import com.example.masterrepo.room.repository.RepoInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import kotlin.random.Random.Default.nextInt

@HiltViewModel
class RoomViewModel @Inject constructor(private val repo: RepoInterface)  : ViewModel(){

    var idLiveData = MutableLiveData<Int>()

    val allEntries = repo.getAllEntries()

    // Inserts the entry to the Room Database
    // Returns true or false depending upon the validation check
    // Return type can be more elaborative showing what exactly went wrong
    fun insert(fName: String, lName:String, age: String, sex: Char): Boolean{
        // Input validation checks
        // No need to perform validation check on sex as that is a spinner item and can't be null or invalid
        if(fName.isEmpty() || lName.isEmpty() || age.isEmpty()){
            return false
        }

        val ageInt = try {
            age.toInt()
        } catch (e: Exception) {
            return false
        }
        // nextInt() is a function which generates random integer
        // An index is generated between 0 and 10000
        val id = nextInt(10000)
        val entry = Entity(fName, lName, ageInt, sex, id)
        viewModelScope.launch {
            repo.insert(entry)
        }
        // Used for testing purposes
        idLiveData.postValue(id)
        return true
    }

    // No validation checks need to be performed here as the entity is always going to be valid as it is fetched from the database
    fun delete(entity: Entity){
        viewModelScope.launch {
            repo.delete(entity)
        }
    }
}