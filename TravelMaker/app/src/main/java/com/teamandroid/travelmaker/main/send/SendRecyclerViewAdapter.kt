package com.teamandroid.travelmaker.main.send

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamandroid.travelmaker.R

class SendRecyclerViewAdapter(var items : ArrayList<SendData>) : RecyclerView.Adapter<SendRecyclerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SendRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_send_item, parent, false)

        return SendRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SendRecyclerViewHolder, position: Int) {
        holder.to.text = items[position].to
        holder.contents.text = items[position].contents
    }

    fun addItem(items : ArrayList<SendData>){
        this.items = items
        notifyDataSetChanged()
    }
}