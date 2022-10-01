package com.example.masterrepo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<CardEntry>() {
        override fun areItemsTheSame(oldItem: CardEntry, newItem: CardEntry): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CardEntry, newItem: CardEntry): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var entries: List<CardEntry>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val titleTextView = holder.itemView.findViewById<TextView>(R.id.textViewRowTitle)
        val descriptionTextView = holder.itemView.findViewById<TextView>(R.id.textViewRowDescription)
        val entry = entries[position]
        holder.itemView.apply {
            titleTextView.text = entry.title
            descriptionTextView.text = entry.description

            setOnClickListener(View.OnClickListener {
                Log.d("RecyclerView", entry.title + " is clicked!")
            })
        }
    }

    override fun getItemCount(): Int {
        return entries.size
    }
}