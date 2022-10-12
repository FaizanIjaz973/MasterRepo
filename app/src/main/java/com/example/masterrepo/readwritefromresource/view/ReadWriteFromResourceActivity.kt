package com.example.masterrepo.readwritefromresource.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.masterrepo.R
import com.example.masterrepo.readwritefromresource.viewmodel.ReadWriteFromResourceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class ReadWriteFromResourceActivity : AppCompatActivity() {

    private val viewModel: ReadWriteFromResourceViewModel by viewModels()

    // Views
    lateinit var editText: EditText
    lateinit var txtView: TextView
    lateinit var buttonWrite: Button
    lateinit var buttonRead: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_readwritefromresource)

        initViews()
        attachOnClickListeners()
        subscribeToObservers()
    }

    private fun subscribeToObservers(){
        viewModel.stringReadFromResource.observe(this, Observer{
            Log.d("ReadWriteFromResourceActivity", it)
            txtView.text = it
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun initViews(){
        editText = findViewById(R.id.readwritefromresourceActivity_editText)
        txtView = findViewById(R.id.readwritefromresourceActivity_txtView)
        buttonWrite = findViewById(R.id.readwritefromresourceActivity_buttonWrite)
        buttonRead = findViewById(R.id.readwritefromresourceActivity_buttonRead)
    }

    private fun attachOnClickListeners(){
        buttonWrite.setOnClickListener(View.OnClickListener {
            viewModel.writeToResource(editText.text.toString())
        })

        buttonRead.setOnClickListener(View.OnClickListener {
            viewModel.readFromResource()
        })
    }
}