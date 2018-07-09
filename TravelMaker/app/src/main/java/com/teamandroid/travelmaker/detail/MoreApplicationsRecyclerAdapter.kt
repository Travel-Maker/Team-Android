package com.teamandroid.travelmaker.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamandroid.travelmaker.Expert
import com.teamandroid.travelmaker.R

class MoreApplicationsRecyclerAdapter(var item : ArrayList<Expert>) : RecyclerView.Adapter<MoreApplicationsRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreApplicationsRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.apply_item, parent, false)

        return MoreApplicationsRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: MoreApplicationsRecyclerViewHolder, position: Int) {
        holder.name.text = item[position].name
        holder.tendency.text = item[position].tendency
        holder.city.text = item[position].city
        holder.ratingBar.rating = item[position].ratingValue
        holder.ratingValue.text = item[position].ratingValue.toString()

        holder.profile.setImageBitmap(item[position].profile)
        holder.crown.setImageBitmap(item[position].crown)
    }
}