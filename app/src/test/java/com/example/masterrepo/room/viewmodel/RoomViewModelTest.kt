package com.example.masterrepo.room.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.masterrepo.MainCoroutineRule
import com.example.masterrepo.getOrAwaitValueTest
import com.example.masterrepo.room.db.Entity
import com.example.masterrepo.room.FakeRepositoryRoom
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RoomViewModelTest(){

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var roomViewModel : RoomViewModel

    @Before
    fun setup(){
        roomViewModel = RoomViewModel(FakeRepositoryRoom())
    }

    @Test
    fun `insertion fails when first name is empty`() {
        val success : Boolean = roomViewModel.insert("","Last Name","32",'M')
        assertThat(success).isFalse()
    }

    @Test
    fun `insertion fails when last name is empty`(){
        val success: Boolean = roomViewModel.insert("First Name", "", "32", 'F')
        assertThat(success).isFalse()
    }

    @Test
    fun `insertion fails when age is empty`() = runBlocking{
        val success: Boolean = roomViewModel.insert("First Name", "Last Name", "", 'M' )
        assertThat(success).isFalse()
    }

    @Test
    fun `insertion is successful when all fields are valid`() = runBlocking{
        val success: Boolean = roomViewModel.insert("First Name", "Last Name", "32", 'M')
        assertThat(success).isTrue()
    }

    @Test
    fun `verify the successful insertion` () = runBlocking {
        roomViewModel.insert("John", "Doe", "32", 'M')
        val id = roomViewModel.idLiveData.getOrAwaitValueTest()
        val entity = Entity("John", "Doe", 32, 'M', id)
        assertThat(roomViewModel.allEntries.getOrAwaitValueTest()).contains(entity)
    }

    @Test
    fun `verify the successful deletion`(){
        roomViewModel.insert("John", "Doe", "32", 'M')
        val id = roomViewModel.idLiveData.getOrAwaitValueTest()
        val entity = Entity("John", "Doe", 32, 'M', id)
        roomViewModel.delete(entity)
        assertThat(roomViewModel.allEntries.getOrAwaitValueTest()).doesNotContain(entity)
    }
}