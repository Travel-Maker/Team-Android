package com.teamandroid.travelmaker.main.send

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_receive_item.view.*
import com.teamandroid.travelmaker.R

class SendRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var to : TextView = itemView.findViewById(R.id.send_to)
    var contents : TextView = itemView.findViewById(R.id.send_contents)
    var confirm : TextView = itemView.findViewById(R.id.send_confirm)
}