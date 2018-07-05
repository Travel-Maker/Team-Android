package com.teamandroid.travelmaker.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamandroid.travelmaker.R

class SearchRecyclerViewAdapter: RecyclerView.Adapter<SearchRecyclerViewHolder>() {
    var items = ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_recyclerview_item, parent, false)
        return SearchRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: SearchRecyclerViewHolder, position: Int) {
        holder.countryName.text = items[position]
    }

    fun addItem(items : ArrayList<String>){
        this.items = items
        this.notifyDataSetChanged()
    }
}