package com.example.masterrepo.readwritefromresource.repository

import android.content.Context
import javax.inject.Inject

class ReadWriteFromResourceRepoImpl @Inject constructor(private val context: Context): ReadWriteFromResourceRepo {

    override suspend fun readFromResource(fileName: String): String {
        return context.openFileInput(fileName).bufferedReader().useLines { lines ->
            lines.fold("") { some, text ->
                "$some\n$text"
            }
        }
    }

    override suspend fun writeToResource(s: String, fileName: String) {
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(s.toByteArray())
        }
    }

    override fun getFilesList(): Array<String> {
        return context.fileList()
    }

}