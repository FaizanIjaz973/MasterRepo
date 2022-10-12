package com.example.masterrepo.readwritefromresource.repository

interface ReadWriteFromResourceRepo {

    suspend fun readFromResource(fileName: String): String

    suspend fun writeToResource(s: String, fileName: String)

    fun getFilesList(): Array<String>

}