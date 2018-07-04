package com.teamandroid.travelmaker.main.favorite

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teamandroid.travelmaker.Expert
import com.teamandroid.travelmaker.R

class PersonRecyclerViewAdapter(var expert: ArrayList<Expert>) : RecyclerView.Adapter<PersonRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_favorite_person_item, parent, false)
        return PersonRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int  = expert.size

    override fun onBindViewHolder(holder: PersonRecyclerViewHolder, position: Int) {

    }
}