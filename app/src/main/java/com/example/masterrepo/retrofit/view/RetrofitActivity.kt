package com.example.masterrepo.retrofit.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.masterrepo.R
import com.example.masterrepo.retrofit.repository.RetrofitRepoImplementation
import com.example.masterrepo.retrofit.viewmodel.RetrofitViewModel
import com.example.masterrepo.util.EspressoIdlingResource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RetrofitActivity : AppCompatActivity() {

    private lateinit var retrofitViewModel: RetrofitViewModel

    @Inject
    lateinit var repoImplementation: RetrofitRepoImplementation

    // Views
    lateinit var textCatFact : TextView
    lateinit var buttonGetCatFact : Button
    lateinit var loadingBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        // Initializing View Model
        retrofitViewModel = RetrofitViewModel(repoImplementation)

        // Subscribing to the observers in viewModel
        subscribeToObservers()

        // Initializing the views
        initViews()
    }

    private fun initViews(){
        textCatFact =  findViewById(R.id.retrofitActivity_textViewCatFact)
        buttonGetCatFact = findViewById(R.id.retrofitActivity_ButtonGetCatFact)
        buttonGetCatFact.setOnClickListener(View.OnClickListener {
            // Fetch data from the API
            makeApiCall()
            buttonGetCatFact.isEnabled = false
        })
        loadingBar = findViewById(R.id.retrofitActivity_loadingBar)
        // Make the loading bar invisible at the start of the activity
        loadingBar.visibility = View.INVISIBLE
    }

    private fun makeApiCall(){
        // Making the call to the API
        retrofitViewModel.getCatFact()
        // Make the loading bar visible
        loadingBar.visibility = View.VISIBLE
    }

    private fun subscribeToObservers(){
        // Listening to the changes to the response in viewModel
        // Will get the API response here
        retrofitViewModel.response.observe(this, Observer {
            if(it != null){
                textCatFact.text = it.fact
            }
            else
                Toast.makeText(this, "Error occurred while fetching data from internet", Toast.LENGTH_LONG).show()

            // Allow the user to make the call again
            buttonGetCatFact.isEnabled = true

            // Make the loading bar invisible
            loadingBar.visibility = View.INVISIBLE
        })
    }
}