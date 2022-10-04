package com.example.masterrepo.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.masterrepo.CardEntry
import com.example.masterrepo.R
import com.example.masterrepo.RecyclerViewAdapter
import com.example.masterrepo.room.db.Entity
import org.w3c.dom.Text
import javax.inject.Inject

class RoomRecyclerViewAdapter @Inject constructor(): RecyclerView.Adapter<RoomRecyclerViewAdapter.RoomViewHolder>() {

    class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<Entity>() {
        override fun areItemsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem == newItem
        }
    }

    private var _clickedItem = MutableLiveData<Entity>()
    var clickedItem : LiveData<Entity> = _clickedItem

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var entries: List<Entity>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_recyclerview_row, parent, false)
        return RoomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val name = holder.itemView.findViewById<TextView>(R.id.roomActivity_recyclerView_row_name)
        val age = holder.itemView.findViewById<TextView>(R.id.roomActivity_recyclerView_row_age)
        val gender = holder.itemView.findViewById<TextView>(R.id.roomActivity_recyclerView_row_gender)
        val id = holder.itemView.findViewById<TextView>(R.id.roomActivity_recyclerView_row_id)

        val deleteButton = holder.itemView.findViewById<Button>(R.id.roomActivity_recyclerView_row_delete_button)
        val entry = entries[position]
        holder.itemView.apply {
            name.text = entry.firstName + " " + entry.lastName
            age.text = entry.age.toString()
            gender.text = entry.sex.toString()
            id.text = entry.id.toString()

            deleteButton.setOnClickListener (View.OnClickListener {
                _clickedItem.postValue(entry)
            })
        }
    }
}