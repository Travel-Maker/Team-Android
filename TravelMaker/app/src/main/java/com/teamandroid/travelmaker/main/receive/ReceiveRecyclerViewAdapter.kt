package com.teamandroid.travelmaker.main.receive

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamandroid.travelmaker.R

class ReceiveRecyclerViewAdapter(var items : ArrayList<ReceiveBoard>) : RecyclerView.Adapter<ReceiveRecyclerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiveRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_receive_item, parent, false)

        return ReceiveRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ReceiveRecyclerViewHolder, position: Int) {
        holder.from.text = items[position].user_nick
        holder.contents.text = items[position].board_data.board_title
    }

    fun addItem(items : ArrayList<ReceiveBoard>){
        this.items = items
        notifyDataSetChanged()
    }
}