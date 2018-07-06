package com.teamandroid.travelmaker.main.receive

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamandroid.travelmaker.R

class ReceiveRecyclerViewAdapter(var items : ArrayList<ReceiveData>) : RecyclerView.Adapter<ReceiveRecyclerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiveRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_receive_item, parent, false)

        return ReceiveRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ReceiveRecyclerViewHolder, position: Int) {
        holder.no.text = (position+1).toString()
        holder.from.text = items[position].from
        holder.contents.text = items[position].contents
    }

    fun addItem(items : ArrayList<ReceiveData>){
        this.items = items
        notifyDataSetChanged()
    }
}