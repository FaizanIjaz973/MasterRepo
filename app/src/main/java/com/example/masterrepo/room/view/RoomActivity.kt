package com.example.masterrepo.room.view

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.masterrepo.R
import com.example.masterrepo.room.adapter.RoomRecyclerViewAdapter
import com.example.masterrepo.room.db.Entity
import com.example.masterrepo.room.repository.RepoImplementation
import com.example.masterrepo.room.viewmodel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random.Default.nextInt


@AndroidEntryPoint
class RoomActivity : AppCompatActivity() {

    private lateinit var roomViewModel: RoomViewModel

    @Inject
    lateinit var repoImplementation: RepoImplementation

    @Inject
    lateinit var roomRecyclerViewAdapter: RoomRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_room)
        Log.d("MasterRepo", "In room Activity")

        // Setting up recycler view
        roomRecyclerViewAdapter = RoomRecyclerViewAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.roomActivity_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = roomRecyclerViewAdapter

        // Initializing View Model
        roomViewModel = RoomViewModel(repoImplementation)

        // Button that saves the entry to the room db
        val buttonAdd = findViewById<Button>(R.id.roomActivity_button_add)
        buttonAdd.setOnClickListener(View.OnClickListener {
            saveToDB()
        })

        // Subscribing to the live data objects in the view model.
        subscribeToObservers()
    }

    // Show custom dialog box.
    // Get inputs.
    // Verify if all inputs are valid.
    // Call Repo function to store the data in the Room database.
    private fun saveToDB(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialogbox_activity_room)

        val fName = dialog.findViewById<EditText>(R.id.activityRoom_edittext_fname)
        val lName = dialog.findViewById<EditText>(R.id.activityRoom_edittext_lname)
        val age = dialog.findViewById<EditText>(R.id.activityRoom_edittext_age)

        val sexSpinner = dialog.findViewById<Spinner>(R.id.roomActivity_spinner)
        val items = listOf("Male", "Female")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        sexSpinner.adapter = adapter

        val save = dialog.findViewById<Button>(R.id.roomActivity_button_dialog_save)
        save.setOnClickListener(View.OnClickListener {

            val selectedSpinnerItemChar = if(sexSpinner.selectedItem.equals("Male")) 'M' else 'F'

            val success = roomViewModel.insert(fName.text.toString(), lName.text.toString(), age.text.toString(), selectedSpinnerItemChar)
            if(success){
                dialog.dismiss()
                Toast.makeText(this, "Entry added successfully", Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(this, "All fields are required.", Toast.LENGTH_LONG).show()
        })

        val cancel = dialog.findViewById<Button>(R.id.roomActivity_button_dialog_cancel)
        cancel.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
        })

        dialog.show()
    }

    private fun subscribeToObservers() {
        // Update all entries in the Database to the RecyclerView
        roomViewModel.allEntries.observe(this, Observer {
            Log.d("RoomDataBaseList", "List of all entries: $it")
            roomRecyclerViewAdapter.entries = it
        })

        // Get the entry that is clicked upon through the recyclerview
        roomRecyclerViewAdapter.clickedItem.observe(this, Observer {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete the entry " + it.firstName + " " + it.lastName + "?")
        builder.setPositiveButton("Yes",
            DialogInterface.OnClickListener { dialog, id ->
                roomViewModel.delete(it)
                dialog.dismiss()
            })
        builder.setNegativeButton("No", DialogInterface.OnClickListener {dialog, id ->
            dialog.dismiss()})
        builder.show()

        })
    }
}